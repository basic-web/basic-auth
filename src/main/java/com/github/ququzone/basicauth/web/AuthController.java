package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.model.User;
import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.ServiceException;
import com.github.ququzone.common.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<String> doLogin(HttpServletRequest request,
                                          HttpServletResponse response,
                                          @RequestParam("username") String username, @RequestParam("password") String password
            , @RequestParam(name = "next", required = false) String next) {
        try {
            User user = authService.login(username, password);
            request.getSession().setAttribute("user", user.getId());
        } catch (ServiceException e) {
            LOG.error("user login exception", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResult.message(e.getMessage()).toString());
        }
        if (next == null || next.isEmpty()) {
            next = "/dashboard";
        }
        Cookie pageCookie = new Cookie("current_page", next);
        pageCookie.setMaxAge(-1);
        pageCookie.setPath("/");
        response.addCookie(pageCookie);

        return ResponseEntity.ok(JsonResult.newJson().add("next", next).toString());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie pageCookie = new Cookie("current_page", "");
        pageCookie.setMaxAge(0);
        pageCookie.setPath("/");
        response.addCookie(pageCookie);
        return "redirect:/login";
    }

    @RequestMapping(value = "/no_permission", method = RequestMethod.GET)
    public String noPermission(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "next", required = false) String next) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("current_page".equals(cookie.getName())) {
                    if (cookie.getValue().equals(next)) {
                        cookie.setMaxAge(0);
                        cookie.setValue("");
                        response.addCookie(cookie);
                    }
                    break;
                }
            }
        }
        return "auth/no_permission";
    }
}
