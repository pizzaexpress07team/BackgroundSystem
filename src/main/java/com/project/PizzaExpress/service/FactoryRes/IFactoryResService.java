package com.project.PizzaExpress.service.FactoryRes;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.FactoryResEntity;

import java.awt.*;
import java.util.List;

public interface IFactoryResService {
    //查
    List<FactoryResEntity> getAllFactoryRes();
    JSONObject getFactoryResByIdLike(String f_id);
    JSONObject getFactoryResById(String f_id);
    JSONObject getFactoryResByNameLike(String r_name);
    JSONObject getFactoryResByName(String r_name);

    //增
    JSONObject addFactoryResItem(String FacResInfo);

    //删

    //改
    JSONObject addFactoryResNum(String f_id,String r_id,int num);
}
