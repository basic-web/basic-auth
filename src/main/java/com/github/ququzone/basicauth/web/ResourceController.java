package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String resources(HttpServletRequest request,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        request.setAttribute("resources", authService.resourcePage(page, Page.DEFAULT_PAGE_SIZE));
        return "auth/resources";
    }
}
