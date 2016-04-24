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
    public ResponseEntity<String> doLogin(HttpServletRequest request,
                                          @RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            User user = authService.login(username, password);
            request.getSession().setAttribute("current_user", user.getId());
        } catch (ServiceException e) {
            LOG.error("user login exception", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(JsonResult.error(e.getMessage()).toString());
        }
        return ResponseEntity.ok(JsonResult.newJson().add("next", "/dashboard").toString());
    }
}
