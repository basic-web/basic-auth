package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.model.Menu;
import com.github.ququzone.basicauth.model.ResourceMapping;
import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @ResourceMapping(name = "菜单管理", pattern = "/menus", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/menus", method = RequestMethod.GET)
    public String menus(HttpServletRequest request) {
        List<Menu> menus = authService.getAllMenus();
        request.setAttribute("menus", menus);
        return "auth/menus";
    }

    @ResourceMapping(name = "菜单管理_添加菜单", pattern = "/menu", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestParam("name") String name, @RequestParam("icon") String icon) {
        authService.addMenu(name, icon);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_查看菜单", pattern = "/menu/{id}", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(authService.getMenu(id)));
    }

    @ResourceMapping(name = "菜单管理_修改菜单", pattern = "/menu/{id}", method = ResourceMapping.RequestMethod.PUT)
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@PathVariable("id") String id,
                                         @RequestParam("name") String name, @RequestParam("icon") String icon) {
        if (!"home".equals(id)) {
            authService.updateMenu(id, name, icon);
        }
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_删除菜单", pattern = "/menu/{id}", method = ResourceMapping.RequestMethod.DELETE)
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        if (!"home".equals(id)) {
            authService.deleteMenu(id);
        }
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_查看菜单资源", pattern = "/menu/{id}/resources", method = ResourceMapping.RequestMethod.GET)
    @RequestMapping(value = "/menu/{id}/resources", method = RequestMethod.GET)
    public ResponseEntity<String> resources(@PathVariable("id") String id) {
        return ResponseEntity.ok(GsonUtil.DEFAULT_GSON.toJson(authService.getUncheckedMenuResource(id)));
    }

    @ResourceMapping(name = "菜单管理_添加菜单资源", pattern = "/menu/{id}/resource", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/menu/{id}/resource", method = RequestMethod.POST)
    public ResponseEntity<String> addResource(@PathVariable("id") String menuId,
                                              @RequestParam("resource") String resourceId) {
        authService.addMenuResource(menuId, resourceId);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_删除菜单资源", pattern = "/menu/{menuId}/resource/{resourceId}", method = ResourceMapping.RequestMethod.DELETE)
    @RequestMapping(value = "/menu/{menuId}/resource/{resourceId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteResource(@PathVariable("menuId") String menuId,
                                                 @PathVariable("resourceId") String resourceId) {
        authService.deleteMenuResource(menuId, resourceId);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_移动菜单", pattern = "/menu/exchange", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/menu/exchange", method = RequestMethod.POST)
    public ResponseEntity<String> exchangeMenu(@RequestParam("previous") String previous,
                                               @RequestParam("next") String next) {
        authService.exchangeMenu(previous, next);
        return ResponseEntity.ok("{}");
    }

    @ResourceMapping(name = "菜单管理_移动菜单资源", pattern = "/menu/{id}/exchange", method = ResourceMapping.RequestMethod.POST)
    @RequestMapping(value = "/menu/{id}/exchange", method = RequestMethod.POST)
    public ResponseEntity<String> exchangeMenuResource(@PathVariable("id") String menuId,
                                                       @RequestParam("previous") String previous,
                                                       @RequestParam("next") String next) {
        authService.exchangeMenuResource(menuId, previous, next);
        return ResponseEntity.ok("{}");
    }
}
