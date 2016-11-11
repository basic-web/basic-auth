package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.model.ResourceMapping;
import com.github.ququzone.basicauth.model.Role;
import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.GsonUtil;
import com.github.ququzone.common.Page;
import com.google.gson.annotations.Expose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * resource controller.
 *
 * @author Yang XuePing
 */
@Controller
public class ResourceController {
    @Autowired
    private AuthService authService;

    @ResourceMapping(name = "资源管理", pattern = "/resources", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/resources", method = RequestMethod.GET)
    public String resources(HttpServletRequest request,
                            @RequestParam(value = "q", required = false) String q,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (q != null && "".equals(q.trim())) {
            q = null;
        }
        request.setAttribute("resources", authService.resourcePage(q, page, Page.DEFAULT_PAGE_SIZE));
        return "auth/resources";
    }

    @ResourceMapping(name = "资源管理_新增资源", pattern = "/resource", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("name") String name, @RequestParam("pattern") String pattern,
                                      @RequestParam("method") ResourceMapping.RequestMethod method) {
        authService.addResource(name, pattern, method);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "资源管理_发现资源", pattern = "/resource/discover", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/resource/discover", method = RequestMethod.POST)
    public ResponseEntity<String> discover() {
        authService.discoverResource();
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "资源管理_查看资源", pattern = "/resource/{id}", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(authService.getResource(id)));
    }

    @ResourceMapping(name = "资源管理_更新资源", pattern = "/resource/{id}", method = ResourceMapping.RequestMethod.PUT)
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> edit(@PathVariable("id") String id, @RequestParam("name") String name,
                                       @RequestParam("pattern") String pattern,
                                       @RequestParam("method") ResourceMapping.RequestMethod method) {
        if (!"dashboard".equals(id)) {
            authService.updateResource(id, name, pattern, method);
        }
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "资源管理_删除资源", pattern = "/resource/{id}", method = ResourceMapping.RequestMethod.DELETE)
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        authService.deleteResource(id);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "资源管理_查看资源所属角色", pattern = "/resource/{id}/roles", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/resource/{id}/roles", method = RequestMethod.GET)
    public ResponseEntity<String> roles(@PathVariable("id") String id) {
        List<Role> all = authService.roles();
        List<Role> userRoles = authService.resourceRoles(id);
        List<ResourceController.ResourceRole> result = all.stream().map(role -> {
            ResourceController.ResourceRole resourceRole = new ResourceController.ResourceRole();
            resourceRole.setId(role.getId());
            resourceRole.setName(role.getName());
            resourceRole.setCreatedTime(role.getCreatedTime());
            resourceRole.setUpdatedTime(role.getUpdatedTime());
            for (Role ur : userRoles) {
                if (ur.getId().equals(role.getId())) {
                    resourceRole.setChecked(true);
                    break;
                }
            }
            return resourceRole;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(result));
    }

    @ResourceMapping(name = "资源管理_更新资源所属角色", pattern = "/resource/{id}/roles", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/resource/{id}/roles", method = RequestMethod.POST)
    public ResponseEntity<String> roles(@PathVariable("id") String id,
                                        @RequestParam(value = "roles", required = false) String[] roles) {
        authService.assignResourceRole(id, roles);
        return ResponseEntity.ok(null);
    }

    private static class ResourceRole extends Role {
        @Expose
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
