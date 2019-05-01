package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResourceEntity {

    private String r_id;
    private String r_name;
    private String r_company;
    private String r_person;
    private String r_type;

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_company() {
        return r_company;
    }

    public void setR_company(String r_company) {
        this.r_company = r_company;
    }

    public String getR_person() {
        return r_person;
    }

    public void setR_person(String r_person) {
        this.r_person = r_person;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public static ResourceEntity fromJsonString(String ResourceInfo){
        ResourceEntity resourceEntity = new ResourceEntity();
        JSONObject jsonObject = JSON.parseObject(ResourceInfo);
        try {
            resourceEntity.setR_id(jsonObject.getString("r_id").toString());
            resourceEntity.setR_name(jsonObject.getString("r_name").toString());
            resourceEntity.setR_company(jsonObject.getString("r_company").toString());
            resourceEntity.setR_person(jsonObject.getString("r_person").toString());
            resourceEntity.setR_type(jsonObject.getString("r_type").toString());
        }catch (NullPointerException e){
            return null;
        }
        return resourceEntity;
    }
}
