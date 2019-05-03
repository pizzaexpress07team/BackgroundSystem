package com.project.PizzaExpress.service.FactoryRes;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.project.PizzaExpress.entity.FactoryResEntity;

import java.awt.*;
import java.util.List;

public interface IFactoryResService {
    //查
    JSONObject getAllFactoryRes();
    JSONObject getAllFactoryResByPage(Integer pno, Integer pageSize);
    JSONObject getFactoryResByFIdLike(String f_id);
    JSONObject getFactoryResByFId(String f_id);
    JSONObject getFactoryResByRNameLike(String r_name);
    JSONObject getFactoryResByRName(String r_name);
    JSONObject getFactoryResByRId(String r_id);
    JSONObject getFactoryResByFIdAndRId(String f_id,String r_id);
    //增
    JSONObject addFactoryResItem(String FacResInfo);

    //删

    //改
    JSONObject updateFactoryResNum(String f_id,String r_id,int num);
}
