package com.github.ququzone.basicauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * dashboard controller.
 *
 * @author Yang XuePing
 */
@Controller
public class DashboardController {
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }
}
