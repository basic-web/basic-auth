package com.github.ququzone.basicauth.service;

import com.github.ququzone.basicauth.model.*;
import com.github.ququzone.basicauth.persistence.*;
import com.github.ququzone.common.MD5;
import com.github.ququzone.common.Page;
import com.github.ququzone.common.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * auth service implement.
 *
 * @author Yang XuePing
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFactMapper userFactMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Value("${password.salt}")
    private String salt;

    @Override
    public User login(String username, String password) {
        password = MD5.digestHexString(salt, password);
        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new ServiceException("用户名或者密码错误");
        }
        if (user.getStatus() != User.Status.NORMAL) {
            throw new ServiceException("用户状态异常");
        }
        return user;
    }

    @Override
    public boolean auditing(String userId, String pattern) {
        Resource resource = resourceMapper.findByPattern(pattern);
        if (resource == null) {
            return true;
        }
        return resourceMapper.countByUserId(userId, resource.getId()) > 0;
    }

    @Override
    public UserVO getUserVO(String userId) {
        return transformUserVO(userMapper.find(userId));
    }

    private UserVO transformUserVO(User user) {
        if (user != null) {
            UserVO result = new UserVO();
            result.setId(user.getId());
            result.setUsername(user.getUsername());
            UserFact userFact = userFactMapper.findByUserIdAndName(user.getId(), UserFact.Field.DISPLAY_NAME);
            if (userFact != null) {
                result.setDisplayName(userFact.getValue());
            }
            result.setStatus(user.getStatus());
            result.setCreatedTime(user.getCreatedTime());
            return result;
        }
        return null;
    }

    @Override
    public List<Menu> getUserMenus(String userId) {
        List<Resource> resources = resourceMapper.findUserResources(userId);
        if (resources != null && !resources.isEmpty()) {
            List<Menu> menus = new LinkedList<>();
            Map<String, Menu> menuMap = new HashMap<>();
            resources.forEach(x -> {
                if (!menuMap.containsKey(x.getMenuId())) {
                    Menu menu = menuMapper.find(x.getMenuId());
                    menus.add(menu);
                    menuMap.put(menu.getId(), menu);
                }
                menuMap.get(x.getMenuId()).addResource(x);
            });
            Collections.sort(menus, (a, b) -> a.getOrderNum().compareTo(b.getOrderNum()));
            return menus;
        }
        return null;
    }

    @Override
    public void settingUser(String userId, String displayName,
                            boolean changePassword, String originPassword, String password) {
        User user = userMapper.find(userId);
        if (user != null) {
            Date now = new Date();
            userFactMapper.updateValueByUserId(userId, UserFact.Field.DISPLAY_NAME, displayName, now);
            if (changePassword) {
                if (!MD5.digestHexString(salt, originPassword).equals(user.getPassword())) {
                    throw new ServiceException("原密码错误");
                }
                userMapper.updatePassword(userId, MD5.digestHexString(salt, password), now);
            }
        }
    }

    @Override
    public Page<UserVO> userPage(int page, int pageSize) {
        Page<UserVO> result = new Page<>();
        result.setTotal(userMapper.count());
        result.setPageSize(pageSize);
        result.setCurrent(page);
        if (result.getTotal() > 0) {
            List<User> users = userMapper.page(pageSize, (page - 1) * pageSize);
            result.setData(users.stream().map(this::transformUserVO).collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    public void addUser(String username, String displayName, String password) {
        if (userMapper.findByUsername(username) != null) {
            throw new ServiceException("用户名已经存在");
        }
        Date now = new Date();
        User user = new User();
        user.generateId();
        user.setUsername(username);
        user.setPassword(MD5.digestHexString(salt, password));
        user.setStatus(User.Status.NORMAL);
        user.setCreatedTime(now);
        userMapper.insert(user);
        UserFact fact = new UserFact();
        fact.generateId();
        fact.setName(UserFact.Field.DISPLAY_NAME);
        fact.setValue(displayName);
        fact.setUserId(user.getId());
        fact.setCreatedTime(now);
        roleMapper.insertUserRole("role_user", user.getId());
        userFactMapper.insert(fact);
    }

    @Override
    public void updateUser(String id, String username, String displayName, String password) {
        User temp = userMapper.findByUsername(username);
        if (temp != null && !temp.getId().equals(id)) {
            throw new ServiceException("用户名已经存在");
        }
        User user = userMapper.find(id);
        if (user != null) {
            Date now = new Date();
            user.setUsername(username);
            user.setPassword(MD5.digestHexString(salt, password));
            user.setUpdatedTime(now);
            userMapper.update(user);
            userFactMapper.updateValueByUserId(id, UserFact.Field.DISPLAY_NAME, displayName, now);
        }
    }

    @Override
    public void disableUser(String id) {
        userMapper.updateStatus(id, User.Status.DISABLE, new Date());
    }

    @Override
    public void enableUser(String id) {
        userMapper.updateStatus(id, User.Status.NORMAL, new Date());
    }

    @Override
    public List<Role> roles() {
        return roleMapper.all();
    }

    @Override
    public List<Role> userRoles(String userId) {
        return roleMapper.userRoles(userId);
    }

    @Override
    public void assignUserRole(String userId, String[] roles) {
        roleMapper.deleteUserRole(userId);
        for (String roleId : roles) {
            roleMapper.insertUserRole(roleId, userId);
        }
    }

    @Override
    public Page<Role> rolePage(int page, int pageSize) {
        Page<Role> result = new Page<>();
        result.setTotal(roleMapper.count());
        result.setPageSize(pageSize);
        result.setCurrent(page);
        if (result.getTotal() > 0) {
            result.setData(roleMapper.page(pageSize, (page - 1) * pageSize));
        }
        return result;
    }

    @Override
    public void addRole(String name) {
        Role role = new Role();
        role.generateId();
        role.setName(name);
        role.setCreatedTime(new Date());
        roleMapper.insert(role);
    }

    @Override
    public Role getRole(String id) {
        return roleMapper.find(id);
    }

    @Override
    public void updateRole(String id, String name) {
        roleMapper.update(id, name, new Date());
    }

    @Override
    public void deleteRole(String id) {
        roleMapper.deleteRoleUser(id);
        roleMapper.deleteRoleResource(id);
        roleMapper.delete(id);
    }

    @Override
    public List<Resource> roleResources(String roleId) {
        return roleMapper.findRoleResources(roleId);
    }

    @Override
    public List<User> roleUsers(String roleId) {
        return roleMapper.findRoleUsers(roleId);
    }

    @Override
    public Page<Resource> resourcePage(int page, int pageSize) {
        Page<Resource> result = new Page<>();
        result.setTotal(resourceMapper.count());
        result.setPageSize(pageSize);
        result.setCurrent(page);
        if (result.getTotal() > 0) {
            result.setData(resourceMapper.page(pageSize, (page - 1) * pageSize));
        }
        return result;
    }

    @Override
    public void addResource(String name, String pattern) {
        Resource resource = new Resource();
        resource.generateId();
        resource.setName(name);
        resource.setPattern(pattern);
        resource.setCreatedTime(new Date());
        resourceMapper.insert(resource);
    }

    @Override
    public Resource getResource(String id) {
        return resourceMapper.find(id);
    }

    @Override
    public void updateResource(String id, String name, String pattern) {
        resourceMapper.update(id, name, pattern, new Date());
    }

    @Override
    public void deleteResource(String id) {
        resourceMapper.deleteResourceRole(id);
        resourceMapper.delete(id);
    }

    @Override
    public List<Role> resourceRoles(String id) {
        return roleMapper.resourceRoles(id);
    }

    @Override
    public void assignResourceRole(String resourceId, String[] roles) {
        roleMapper.deleteResourceRole(resourceId);
        for (String roleId : roles) {
            roleMapper.insertResourceRole(roleId, resourceId);
        }
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menus = menuMapper.all();
        menus.forEach(menu -> menu.setResources(resourceMapper.findMenuResources(menu.getId())));
        return menus;
    }

    @Override
    public void addMenu(String name, String icon) {
        Menu menu = new Menu();
        menu.generateId();
        menu.setName(name);
        menu.setIcon(icon);
        menu.setOrderNum(menuMapper.count() + 1);
        menu.setCreatedTime(new Date());
        menuMapper.insert(menu);
    }

    @Override
    public Menu getMenu(String id) {
        return menuMapper.find(id);
    }

    @Override
    public void updateMenu(String id, String name, String icon) {
        menuMapper.update(id, name, icon, new Date());
    }
}
