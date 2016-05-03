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

    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("name") String name, @RequestParam("pattern") String pattern) {
        authService.addResource(name, pattern);
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(authService.getResource(id)));
    }

    @RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> edit(@PathVariable("id") String id, @RequestParam("name") String name,
                                       @RequestParam("pattern") String pattern) {
        if (!"dashboard".equals(id)) {
            authService.updateResource(id, name, pattern);
        }
        return ResponseEntity.ok("{}");
    }

    @RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        authService.deleteResource(id);
        return ResponseEntity.ok("{}");
    }
}
