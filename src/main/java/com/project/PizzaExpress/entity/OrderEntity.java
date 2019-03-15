package com.project.PizzaExpress.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderEntity {

    private long Id;
    private long uId;
    private Timestamp createTime;
    private Timestamp payTime;
    private int deliveryState;
    private long fId;
    private long dId;
    private String detail;
    private BigDecimal totalPrice;
    private int payState;
    private String deliveryAddr;
    private long payId;

    public void setoId(long Id) {
        this.Id = Id;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public void setDeliveryState(int deliveryState) {
        this.deliveryState = deliveryState;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public void setdId(long dId) {
        this.dId = dId;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public void setPayId(long payId) {
        this.payId = payId;
    }

    public long getoId() {
        return Id;
    }

    public long getuId() {
        return uId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public int getDeliveryState() {
        return deliveryState;
    }

    public long getfId() {
        return fId;
    }

    public long getdId() {
        return dId;
    }

    public String getDetail() {
        return detail;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getPayState() {
        return payState;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public long getPayId() {
        return payId;
    }
}
