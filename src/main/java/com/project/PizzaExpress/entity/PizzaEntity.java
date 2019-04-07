package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.UUID;

public class PizzaEntity {

    private String p_type;
    private String p_id;
    private String p_name;
    private BigDecimal price;
    private boolean is_empty;
    private String p_picture;
    private String f_id;
    private String p_size;

    public String getP_type() {
        return p_type;
    }

    public String getP_id() {
        return p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isIs_empty() {
        return is_empty;
    }

    public String getP_picture() {
        return p_picture;
    }

    public String getF_id() {
        return f_id;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setIs_empty(boolean is_empty) {
        this.is_empty = is_empty;
    }

    public void setP_picture(String p_picture) {
        this.p_picture = p_picture;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public String toString()
    {
        return  "[p_type : " + p_type + "] " +
                "[p_id : " + p_id + "] " +
                "[p_name : " + p_name + "] " +
                "[price : " + price + "] " +
                "[is_empty : " + is_empty + "] " +
                "[p_picture : " + p_picture + "] " +
                "[f_id : " + f_id + "] " +
                "[p_size : " + p_size + "] ";
    }

    public static PizzaEntity fromJsonString(String pizzaInfo, boolean isUpdate)
    {
        PizzaEntity pizzaEntity = new PizzaEntity();
        JSONObject jsonObject = JSON.parseObject(pizzaInfo);

        pizzaEntity.setP_type(jsonObject.getString("p_type"));
        if (isUpdate)
        {
            pizzaEntity.setP_id(jsonObject.getString("p_id"));
        }
        else
        {
            String pid = UUID.randomUUID().toString().replaceAll("-", "");
            pizzaEntity.setP_id(pid);
        }
        pizzaEntity.setP_name(jsonObject.getString("p_name"));
        pizzaEntity.setPrice(jsonObject.getBigDecimal("price"));
        pizzaEntity.setIs_empty(jsonObject.getBoolean("is_empty"));
        pizzaEntity.setP_picture(jsonObject.getString("p_picture"));
        pizzaEntity.setF_id(jsonObject.getString("f_id"));
        pizzaEntity.setP_size(jsonObject.getString("p_size"));

        return pizzaEntity;
    }
}
