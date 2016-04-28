package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;

/**
 * resource model.
 *
 * @author Yang XuePing
 */
public class Resource extends AbstractModel {
    @Expose
    private String name;
    @Expose
    private String pattern;
    private String menuId;
    private Integer orderNum;

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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
