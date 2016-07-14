package com.github.ququzone.basicauth.web;

import com.github.ququzone.basicauth.model.ResourceMapping;
import com.github.ququzone.basicauth.service.AuthService;
import com.github.ququzone.common.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * auth interceptor.
 *
 * @author Yang XuePing
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().substring(
                request.getContextPath().length());
        boolean ajax = request.getHeader("X-Requested-With") != null && !request.getHeader("X-Requested-With").isEmpty();
        if (requestPath.endsWith(".jsp")) {
            return true;
        }
        String userId = (String) request.getSession().getAttribute("user");
        if (userId == null || userId.isEmpty()) {
            if (LOG.isInfoEnabled()) {
                LOG.info("user not login for request:" + requestPath);
            }
            response.setStatus(400);
            if (ajax) {
                response.getWriter().println(JsonResult.message("你尚未登录系统"));
            } else {
                response.sendRedirect("/login?next=" + requestPath);
            }
            return false;
        }
        if (!authService.auditing(userId, requestPath,
                ResourceMapping.RequestMethod.valueOf(request.getMethod()))) {
            if (LOG.isInfoEnabled()) {
                LOG.info("user not permission for request:" + requestPath);
            }
            response.setStatus(400);
            if (ajax) {
                response.getWriter().println(JsonResult.message("没有权限访问"));
            } else {
                response.sendRedirect("/no_permission?next=" + requestPath);
            }
            return false;
        }
        return true;
    }
}
