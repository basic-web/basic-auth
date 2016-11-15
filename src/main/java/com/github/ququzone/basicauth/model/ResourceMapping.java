package com.github.ququzone.basicauth.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * resource mapping.
 *
 * @author Yang XuePing
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceMapping {
    String name();

    String pattern();

    String menu() default "";

    String menuIcon() default "";

    String roles() default "";

    RequestMethod method();

    enum RequestMethod {
        ALL,
        GET,
        HEAD,
        POST,
        PUT,
        PATCH,
        DELETE,
        OPTIONS,
        TRACE;

        private RequestMethod() {
        }
    }
}
