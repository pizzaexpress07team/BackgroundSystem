package com.project.PizzaExpress.service.menu.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
            List<PizzaEntity> not_empty = new LinkedList<>();
            for (PizzaEntity pe : pizzaList)
            {
                if (!pe.isIs_empty()) //不想前端传输is_empty为true的pizza数据
                {
                    not_empty.add(pe);//todo 不传输部分属性
                }
            }
            result.put("list", not_empty);
            result.put("total", not_empty.size());
        }

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
