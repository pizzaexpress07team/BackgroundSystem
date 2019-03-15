package com.project.PizzaExpress.entity;

import java.sql.Timestamp;

public class DeliverymanEntity {

    private long Id;
    private String name;
    private String phone;
    private Timestamp createTime;
    private String fId;

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getfId() {
        return fId;
    }
}
