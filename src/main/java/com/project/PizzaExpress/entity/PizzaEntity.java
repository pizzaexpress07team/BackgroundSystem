package com.project.PizzaExpress.entity;

import java.math.BigDecimal;

public class PizzaEntity {


    private String p_type;
    private long p_id;
    private String p_name;
    private BigDecimal price;
    private boolean is_empty;
    private String p_picture;
    private long f_id;
    private String p_size;

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public long getP_id() {
        return p_id;
    }

    public void setP_id(long p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isIs_empty() {
        return is_empty;
    }

    public void setIs_empty(boolean is_empty) {
        this.is_empty = is_empty;
    }

    public String getP_picture() {
        return p_picture;
    }

    public void setP_picture(String p_picture) {
        this.p_picture = p_picture;
    }

    public long getF_id() {
        return f_id;
    }

    public void setF_id(long f_id) {
        this.f_id = f_id;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public String toString()
    {
        return  "[type : " + p_type + "] " +
                "[id : " + p_id + "] " +
                "[name : " + p_name + "] " +
                "[price : " + price + "] " +
                "[isEmpty : " + is_empty + "] " +
                "[picture : " + p_picture + "] " +
                "[fId : " + f_id + "] " +
                "[size : " + p_size + "] ";
    }
}
