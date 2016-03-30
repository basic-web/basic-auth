package com.github.ququzone.basicauth.model;

/**
 * role model.
 *
 * @author Yang XuePing
 */
public class Role extends AbstractModel {
    private String name;
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        NORMAL, DISABLE
    }
}
