package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;

/**
 * role model.
 *
 * @author Yang XuePing
 */
public class Role extends AbstractModel {
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
