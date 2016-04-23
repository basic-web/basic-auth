package com.github.ququzone.basicauth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * auth service implement.
 *
 * @author Yang XuePing
 */
@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    public void login(String username, String password) {
        // TODO
    }
}
