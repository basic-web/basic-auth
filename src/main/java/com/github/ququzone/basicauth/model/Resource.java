package com.github.ququzone.basicauth.model;

/**
 * resource model.
 *
 * @author Yang XuePing
 */
public class Resource extends AbstractModel {
    private String name;
    private String pattern;
    private Method method;
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Method {
        GET, POST, PUT, DELETE
    }

    public enum Status {
        NORMAL, DISABLE
    }
}
