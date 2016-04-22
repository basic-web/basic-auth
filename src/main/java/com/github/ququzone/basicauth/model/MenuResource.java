package com.github.ququzone.basicauth.model;

/**
 * menu resource model.
 *
 * @author Yang XuePing
 */
public class MenuResource extends AbstractModel {
    private String menuId;

    private String resourceId;

    private Integer orderNum;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
