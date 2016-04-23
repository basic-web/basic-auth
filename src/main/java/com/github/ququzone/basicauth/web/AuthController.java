package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * basic auth controller.
 *
 * @author Yang XuePing
 */
@Controller
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request,
                          @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            authService.login(username, password);
        } catch (ServiceException e) {
            LOG.error("user login exception", e);
            request.setAttribute("message", e.getMessage());
            return "error";
        }
        return "redirect:/dashboard";
    }
}
