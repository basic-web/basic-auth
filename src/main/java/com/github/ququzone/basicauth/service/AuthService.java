package com.github.ququzone.basicauth.service;

import com.github.ququzone.basicauth.model.User;

/**
 * auth service.
 *
 * @author Yang XuePing
 */
public interface AuthService {
    User login(String username, String password);

    boolean auditing(String userId, String pattern);
}
