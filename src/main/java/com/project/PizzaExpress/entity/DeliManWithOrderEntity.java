package com.project.PizzaExpress.entity;

import java.math.BigDecimal;

public class DeliManWithOrderEntity extends OrderEntity{
    private String d_name;
    private String d_phone;
    private String uid;
    private BigDecimal lng;
    private BigDecimal lat;
    private int state;

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_phone() {
        return d_phone;
    }

    public void setD_phone(String d_phone) {
        this.d_phone = d_phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
