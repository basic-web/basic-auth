package com.github.ququzone.basicauth.model;

/**
 * menu model.
 *
 * @author Yang XuePing
 */
public class Menu extends AbstractModel {
    private String name;

    private String icon;

    private Integer orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
