package com.project.PizzaExpress.entity;

public class FactoryResourceEntity {

    private String f_id;
    private String r_id;
    private int r_num;

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public int getR_num() {
        return r_num;
    }

    public void setR_num(int r_num) {
        this.r_num = r_num;
    }

    public boolean changeResNum(int change)
    {
        int after = r_num + change;
        if (after >= 0)
        {
            r_num = after;
            return true;
        }
        return false;
    }
}
