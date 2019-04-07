package com.project.PizzaExpress.service.menuInfo.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        JSONObject result = new JSONObject();
        if (pizzaList == null || pizzaList.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Menu has not been found or it is empty!");
        }
        else
        {
            result.put("errorCode", 0);
            result.put("list", pizzaList);
            result.put("total", pizzaList.size());
        }

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
