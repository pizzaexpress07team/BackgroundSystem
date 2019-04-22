package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class OrderEntity {

    private String o_id;
    private String u_id;
    private Timestamp o_create_time;
    private Timestamp o_pay_time;
    private int delivery_state;
    private String f_id;
    private String d_id;
    private String detail;
    private BigDecimal total_price;
    private int o_pay_state;
    private String o_delivery_addr;
    private String pay_id;

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public Timestamp getO_create_time() {
        return o_create_time;
    }

    public void setO_create_time(Timestamp o_create_time) {
        this.o_create_time = o_create_time;
    }

    public Timestamp getO_pay_time() {
        return o_pay_time;
    }

    public void setO_pay_time(Timestamp o_pay_time) {
        this.o_pay_time = o_pay_time;
    }

    public int getDelivery_state() {
        return delivery_state;
    }

    public void setDelivery_state(int delivery_tate) {
        this.delivery_state = delivery_tate;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public int getO_pay_state() {
        return o_pay_state;
    }

    public void setO_pay_state(int o_pay_state) {
        this.o_pay_state = o_pay_state;
    }

    public String getO_delivery_addr() {
        return o_delivery_addr;
    }

    public void setO_delivery_addr(String o_delivery_addr) {
        this.o_delivery_addr = o_delivery_addr;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public static OrderEntity fromJsonString(String orderInfo, boolean isUpdate)
    {
        OrderEntity orderEntity = new OrderEntity();
        JSONObject jsonObject = JSON.parseObject(orderInfo);

        if (isUpdate)
        {
            orderEntity.setO_id(jsonObject.getString("o_id"));
            Timestamp opt = jsonObject.getTimestamp("o_pay_time");
            if (opt != null)
                orderEntity.setO_pay_time(opt);
            orderEntity.setDelivery_state(jsonObject.getInteger("delivery_state"));
            orderEntity.setO_pay_state(jsonObject.getInteger("o_pay_state"));

        }
        else
        {
            String pid = UUID.randomUUID().toString().replaceAll("-", "");
            orderEntity.setO_id(pid);
            orderEntity.setO_create_time(new Timestamp(new Date().getTime()));
            orderEntity.setDelivery_state(0);
            orderEntity.setO_pay_state(0);
        }
        orderEntity.setU_id(jsonObject.getString("u_id"));
        orderEntity.setF_id(jsonObject.getString("f_id"));
        orderEntity.setD_id(jsonObject.getString("d_id"));
        orderEntity.setDetail(jsonObject.getString("detail"));
        orderEntity.setTotal_price(jsonObject.getBigDecimal("total_price"));
        orderEntity.setO_delivery_addr(jsonObject.getString("o_delivery_addr"));
        orderEntity.setPay_id(jsonObject.getString("pay_id"));

        return orderEntity;
    }

}
