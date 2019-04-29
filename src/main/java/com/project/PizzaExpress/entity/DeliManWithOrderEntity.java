package com.project.PizzaExpress.entity;

import java.math.BigDecimal;

public class DeliManWithOrderEntity extends OrderEntity{
    private String d_name;
    private String d_phone;
    private String u_id;
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

    @Override
    public String getU_id() {
        return u_id;
    }

    @Override
    public void setU_id(String u_id) {
        this.u_id = u_id;
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
