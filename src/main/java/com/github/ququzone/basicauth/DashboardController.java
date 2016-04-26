package com.github.ququzone.basicauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * dashboard controller.
 *
 * @author Yang XuePing
 */
@Controller
public class DashboardController {
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpServletResponse response) {
        Cookie pageCookie = new Cookie("current_page", "/dashboard");
        pageCookie.setMaxAge(-1);
        pageCookie.setPath("/");
        response.addCookie(pageCookie);
        return "dashboard";
    }
}
