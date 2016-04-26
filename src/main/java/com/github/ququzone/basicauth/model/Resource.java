package com.github.ququzone.basicauth.model;

/**
 * resource model.
 *
 * @author Yang XuePing
 */
public class Resource extends AbstractModel {
    private String name;
    private String pattern;
    private Status status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public enum Status {
        NORMAL, DISABLE
    }
}
