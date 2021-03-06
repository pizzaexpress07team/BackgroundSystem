package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class UserEntity {

    private String uid;
    private String username;
    private String password;
    private String addr;
    private int is_admin;
    private String phone;
    private String sina;
    private String qq;
    private Timestamp create_time;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSina() {
        return sina;
    }

    public void setSina(String sina) {
        this.sina = sina;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("addr", addr);
        jsonObject.put("is_admin", is_admin);
        jsonObject.put("phone", phone);
        jsonObject.put("sina", sina);
        jsonObject.put("qq", qq);
        jsonObject.put("create_time", create_time.toString());

        return jsonObject;
    }

    public static UserEntity fromJsonString(String userInfo, boolean isUpdate)
    {
        UserEntity userEntity = new UserEntity();
        JSONObject jsonObject = JSON.parseObject(userInfo);

        //用户信息插入
        if (isUpdate) {
            userEntity.setUid(jsonObject.getString("uid"));
            userEntity.setAddr(jsonObject.getString("addr"));
            userEntity.setIs_admin(jsonObject.getBigDecimal("is_admin").intValue());
            userEntity.setPhone(jsonObject.getString("phone"));
            userEntity.setSina(jsonObject.getString("sina"));
            userEntity.setQq(jsonObject.getString("qq"));
        }
        else {
            String uid = UUID.randomUUID().toString().replaceAll("-", "");
            userEntity.setUid(uid);
            userEntity.setCreate_time(new Timestamp(new Date().getTime()));
        }

        try{
            userEntity.setUsername(jsonObject.getString("username").toString());
            userEntity.setPassword(jsonObject.getString("password").toString());

        }catch (Exception e) {
            return null;
        }

        return userEntity;
    }
}
