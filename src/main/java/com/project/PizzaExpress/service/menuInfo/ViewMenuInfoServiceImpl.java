package com.project.PizzaExpress.service.menuInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewMenuInfoServiceImpl implements  IViewMenuInfoService {

    @Autowired
    private PizzaDAO pizzaDAO;

    public String displayMenu()
    {
        List<PizzaEntity> pizzaList = pizzaDAO.queryAll();
//        for (PizzaEntity pe : pizzaList)
//        {
//            System.out.println(pe.toString());
//        }
        return JSON.toJSONString(pizzaList, SerializerFeature.WriteMapNullValue);
    }
}
