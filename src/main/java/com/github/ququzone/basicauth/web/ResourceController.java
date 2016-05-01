package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * resource controller.
 *
 * @author Yang XuePing
 */
@Controller
public class ResourceController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public String resources() {
        return "auth/resources";
    }
}
