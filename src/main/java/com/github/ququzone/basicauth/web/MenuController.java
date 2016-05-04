package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.model.Menu;
import com.github.ququzone.basicauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * menu controller.
 *
 * @author Yang XuePing
 */
@Controller
public class MenuController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String menus(HttpServletRequest request) {
        List<Menu> menus = authService.getAllMenus();
        request.setAttribute("menus", menus);
        return "auth/menus";
    }
}
