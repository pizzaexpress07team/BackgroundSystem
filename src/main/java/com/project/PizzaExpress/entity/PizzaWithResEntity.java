package com.project.PizzaExpress.entity;

public class PizzaWithResEntity extends PizzaEntity{

    private String resource;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public static PizzaWithResEntity transFromPizzaEntity(PizzaEntity pe)
    {
        if (pe == null)
            return null;
        PizzaWithResEntity _pe = new PizzaWithResEntity();
        _pe.setP_type(pe.getP_type());
        _pe.setP_id(pe.getP_id());
        _pe.setP_name(pe.getP_name());
        _pe.setPrice(pe.getPrice());
        _pe.setIs_empty(pe.isIs_empty());
        _pe.setP_picture(pe.getP_picture());
        _pe.setF_id(pe.getF_id());
        _pe.setP_size(pe.getP_size());
        return _pe;
    }

    public static PizzaWithResEntity fromJsonString(String pizzaInfo, boolean isUpdate)
    {
        return transFromPizzaEntity(fromJsonString(pizzaInfo, isUpdate));
    }

}
