package com.github.ququzone.basicauth.service;

import com.github.ququzone.basicauth.model.*;
import com.github.ququzone.common.Page;

import java.util.List;

/**
 * auth service.
 *
 * @author Yang XuePing
 */
public interface AuthService {
    User login(String username, String password);

    boolean auditing(String userId, String pattern);

    UserVO getUserVO(String userId);

    List<Menu> getUserMenus(String userId);

    void settingUser(String userId, String displayName, boolean changePassword, String originPassword, String password);

    Page<UserVO> userPage(int page, int pageSize);

    void addUser(String username, String displayName, String password);

    void updateUser(String id, String username, String displayName, String password);

    void disableUser(String id);

    void enableUser(String id);

    List<Role> roles();

    List<Role> userRoles(String userId);

    void assignUserRole(String userId, String[] roles);

    Page<Role> rolePage(int page, int pageSize);

    void addRole(String name);

    Role getRole(String id);

    void updateRole(String id, String name);

    void deleteRole(String id);

    List<Resource> roleResources(String roleId);

    List<User> roleUsers(String roleId);

    Page<Resource> resourcePage(int page, int pageSize);

    void addResource(String name, String pattern);

    Resource getResource(String id);

    void updateResource(String id, String name, String pattern);

    void deleteResource(String id);

    List<Role> resourceRoles(String id);

    void assignResourceRole(String id, String[] roles);

    List<Menu> getAllMenus();
}
