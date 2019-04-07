package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.UUID;

public class DeliverymanEntity {

    private String d_id;
    private String d_name;
    private String d_phone;
    private Timestamp create_time;
    private String f_id;

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

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

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public static DeliverymanEntity fromJsonString(String deliverymanInfo, boolean isUpdate)
    {
        DeliverymanEntity deliverymanEntity = new DeliverymanEntity();
        JSONObject jsonObject = JSON.parseObject(deliverymanInfo);

        if (isUpdate)
        {
            deliverymanEntity.setD_id(jsonObject.getString("d_id"));
        }
        else {
            String did = UUID.randomUUID().toString().replaceAll("-", "");
            deliverymanEntity.setD_id(did);
        }
        deliverymanEntity.setD_name(jsonObject.getString("d_name"));
        deliverymanEntity.setD_phone(jsonObject.getString("d_phone"));
        deliverymanEntity.setCreate_time(jsonObject.getTimestamp("create_time"));
        deliverymanEntity.setF_id(jsonObject.getString("f_id"));

        return deliverymanEntity;
    }
}
