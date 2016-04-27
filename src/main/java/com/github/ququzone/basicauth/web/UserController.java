package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.GsonUtil;
import com.github.ququzone.common.ServiceException;
import com.github.ququzone.common.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/nav", method = {RequestMethod.GET, RequestMethod.POST})
    public String nav(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("user");
        request.setAttribute("user", authService.getUserVO(userId));
        request.setAttribute("menus", authService.getUserMenus(userId));
        return "include/nav";
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public String settings(HttpServletRequest request) {
        request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
        return "auth/settings";
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.POST)
    public String doSettings(
            HttpServletRequest request,
            @RequestParam(name = "display_name") String displayName,
            @RequestParam(name = "change_password", required = false) boolean changePassword,
            @RequestParam(name = "origin_password", required = false) String originPassword,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "repassword", required = false) String repassword) {
        if (displayName == null || displayName.isEmpty()) {
            request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, "姓名不能为空"));
            request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
            return "auth/settings";
        }
        if (changePassword) {
            if (originPassword == null || originPassword.isEmpty()) {
                request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, "原密码不能为空"));
                request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
                return "auth/settings";
            }
            if (password == null || password.isEmpty()) {
                request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, "密码不能为空"));
                request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
                return "auth/settings";
            }
            if (repassword == null || repassword.isEmpty()) {
                request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, "重复密码不能为空"));
                request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
                return "auth/settings";
            }
            if (!password.equals(repassword)) {
                request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, "两次输入密码不一致"));
                request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
                return "auth/settings";
            }
        }
        try {
            authService.settingUser((String) request.getSession().getAttribute("user"), displayName, changePassword, originPassword, password);
        } catch (ServiceException e) {
            request.setAttribute("flashMessage", new FlashMessage(FlashMessage.Level.error, e.getMessage()));
            request.setAttribute("user", authService.getUserVO((String) request.getSession().getAttribute("user")));
            return "auth/settings";
        }
        return "redirect:/user/settings";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users() {
        // TODO
        return "auth/users";
    }
}
