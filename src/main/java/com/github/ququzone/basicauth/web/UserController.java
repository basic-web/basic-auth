package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * user controller.
 *
 * @author Yang XuePing
 */
@Controller
public class UserController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public
    @ResponseBody
    String user(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("user");
        return GsonUtil.DEFAULT_GSON.toJson(authService.getUserVO(userId));
    }

    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public String nav(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("user");
        request.setAttribute("user", authService.getUserVO(userId));
        request.setAttribute("menus", "");
        return "include/nav";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {
        // TODO
        return "auth/users";
    }
}
