package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.GsonUtil;
import com.github.ququzone.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * role controller.
 *
 * @author Yang XuePing
 */
@Controller
public class RoleController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String roles(HttpServletRequest request,
                        @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        request.setAttribute("roles", authService.rolePage(page, Page.DEFAULT_PAGE_SIZE));
        return "auth/roles";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("name") String name) {
        authService.addRole(name);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(authService.getRole(id)));
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> edit(@PathVariable("id") String id, @RequestParam("name") String name) {
        if (!"role_user".equals(id) && !"role_admin".equals(id)) {
            authService.updateRole(id, name);
        }
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        authService.deleteRole(id);
        return ResponseEntity.ok("{}");
    }
}
