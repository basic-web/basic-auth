package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * user vo.
 *
 * @author Yang XuePing
 */
public class UserVO {
    @Expose
    private String id;
    @Expose
    private String username;
    @Expose
    private String displayName;
    @Expose
    private Date createdTime;
    @Expose
    private User.Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public User.Status getStatus() {
        return status;
    }

    public void setStatus(User.Status status) {
        this.status = status;
    }
}
