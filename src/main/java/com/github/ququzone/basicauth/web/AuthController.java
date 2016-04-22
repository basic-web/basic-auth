package com.github.ququzone.basicauth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * basic auth controller.
 *
 * @author Yang XuePing
 */
@Controller
public class AuthController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }
}
