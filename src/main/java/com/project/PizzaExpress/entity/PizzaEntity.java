package com.project.PizzaExpress.entity;

import java.math.BigDecimal;

public class PizzaEntity {

    private String type;
    private long id;
    private String name;
    private BigDecimal price;
    private boolean isEmpty;
    private String picture;
    private long fId;
    private String size;

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public String getPicture() {
        return picture;
    }

    public long getfId() {
        return fId;
    }

    public String getSize() {
        return size;
    }
}
