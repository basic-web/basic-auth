package com.github.ququzone.basicauth.service;

import com.github.ququzone.basicauth.model.Menu;
import com.github.ququzone.basicauth.model.User;
import com.github.ququzone.basicauth.model.UserVO;

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
}
