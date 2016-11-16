package com.github.ququzone.basicauth;

import com.github.ququzone.common.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * exception controller.
 *
 * @author Yang XuePing
 */
@ControllerAdvice
public class ExceptionController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    public void handle(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LOG.error("handle controller exception", e);
        try {
            boolean ajax = request.getHeader("X-Requested-With") != null && !request.getHeader("X-Requested-With").isEmpty();
            response.setStatus(500);
            if (ajax) {
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.getWriter().println(JsonResult.message(e.getMessage()));
            } else {
                request.setAttribute("exception", e);
                request.getServletContext().getRequestDispatcher("/WEB-INF/views/exception.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            LOG.error("handle exception occur error", ex);
        }
    }
}
