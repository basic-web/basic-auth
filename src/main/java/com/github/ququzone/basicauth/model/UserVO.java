package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;

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
}
