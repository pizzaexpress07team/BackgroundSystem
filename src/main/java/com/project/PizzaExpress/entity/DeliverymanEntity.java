package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.Null;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class DeliverymanEntity {

    private String d_id;
    private String d_name;
    private String d_phone;
    private String f_id;
    private String uid;
    private BigDecimal lng;
    private BigDecimal lat;
    private int state;

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

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
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

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("d_id", d_id);
        jsonObject.put("d_name", d_name);
        jsonObject.put("d_phone", d_phone);
        jsonObject.put("f_id", f_id);
        jsonObject.put("uid", uid);
        jsonObject.put("lng", lng);
        jsonObject.put("lat", lat);
        jsonObject.put("state", state);

        return jsonObject;
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
            deliverymanEntity.setUid(jsonObject.getString("uid"));
            try{
                deliverymanEntity.setD_name(jsonObject.getString("d_name").toString());
                deliverymanEntity.setD_phone(jsonObject.getString("d_phone").toString());
                deliverymanEntity.setF_id(jsonObject.getString("f_id").toString());
            }catch (NullPointerException e)
            {
                return null;
            }
        }
        deliverymanEntity.setLng(jsonObject.getBigDecimal("lng"));
        deliverymanEntity.setLat(jsonObject.getBigDecimal("lat"));
        deliverymanEntity.setState(jsonObject.getInteger("state"));

        return deliverymanEntity;
    }
}
