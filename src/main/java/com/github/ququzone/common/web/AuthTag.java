package com.github.ququzone.common.web;

import com.github.ququzone.basicauth.model.ResourceMapping;
import com.github.ququzone.basicauth.service.AuthService;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * pagination tag.
 *
 * @author Yang XuePing
 */
public class AuthTag extends SimpleTagSupport {
    private String pattern;
    private String method;

    public void doTag() throws JspException, IOException {
        PageContext context = (PageContext) getJspContext();
        String userId = (String) context.getSession().getAttribute("user");
        if (userId != null && !userId.isEmpty()) {
            AuthService authService = WebApplicationContextUtils
                    .getWebApplicationContext(context.getServletContext()).getBean(AuthService.class);
            if (authService.auditing(userId, pattern, ResourceMapping.RequestMethod.valueOf(method))) {
                getJspBody().invoke(getJspContext().getOut());
            }
        }
        super.doTag();
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
