package com.github.ququzone.basicauth.model;

import com.google.gson.annotations.Expose;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * abstract model.
 *
 * @author Yang XuePing
 */
public class AbstractModel {
    @Expose
    private String id;
    @Expose
    private Date createdTime;
    @Expose
    private Date updatedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void generateId() {
        if (id == null || "".equals(id))
            id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return String.format("%s:[%s]", getClass().getCanonicalName(), this.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equals(obj, this);
    }
}
