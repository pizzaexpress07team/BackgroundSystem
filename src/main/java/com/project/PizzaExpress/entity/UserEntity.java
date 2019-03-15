package com.project.PizzaExpress.entity;

import java.sql.Timestamp;

public class UserEntity {

    private String Id;
    private String name;
    private String password;
    private String addr;
    private int isAdmin;
    private String phone;
    private String sina;
    private String qq;
    private Timestamp createTime;

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSina(String sina) {
        this.sina = sina;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddr() {
        return addr;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public String getPhone() {
        return phone;
    }

    public String getSina() {
        return sina;
    }

    public String getQq() {
        return qq;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
