package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;

/**
 * user model.
 *
 * @author Yang XuePing
 */
public class User extends AbstractModel {
    @Expose
    private String username;
    private String password;
    private Status status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
