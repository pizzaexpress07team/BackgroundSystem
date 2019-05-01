package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

public class FactoryResEntity {

    private String f_id;
    private String r_id;
    private int r_num;
    private String r_name;
    private String r_company;
    private String r_person;
    private String r_type;


    public String getF_id() { return f_id; }

    public void setF_id(String f_id) { this.f_id = f_id; }

    public String getR_id() { return r_id; }

    public void setR_id(String r_id) { this.r_id = r_id; }

    public int getR_num() { return r_num; }

    public void setR_num(int r_num) { this.r_num = r_num; }

    public String getR_name() { return r_name; }

    public void setR_name(String r_name) { this.r_name = r_name; }

    public String getR_company() { return r_company; }

    public void setR_company(String r_company) { this.r_company = r_company; }

    public String getR_person() { return r_person; }

    public void setR_person(String r_person) { this.r_person = r_person; }

    public String getR_type() { return r_type; }

    public void setR_type(String r_type) { this.r_type = r_type; }

    public static FactoryResEntity fromJsonString(String FactoryResInfo){
        FactoryResEntity factoryResEntity = new FactoryResEntity();
        JSONObject jsonObject = JSON.parseObject(FactoryResInfo);
        try {
            factoryResEntity.setF_id(jsonObject.getString("f_id").toString());
            factoryResEntity.setR_id(jsonObject.getString("r_id").toString());
            factoryResEntity.setR_num(jsonObject.getInteger("r_num"));
            factoryResEntity.setR_name(jsonObject.getString("r_name").toString());
            factoryResEntity.setR_company(jsonObject.getString("r_company").toString());
            factoryResEntity.setR_person(jsonObject.getString("r_person").toString());
            factoryResEntity.setR_type(jsonObject.getString("r_type").toString());
        }catch (NullPointerException e){
            return null;
        }
        return factoryResEntity;
    }
}
