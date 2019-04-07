package com.project.PizzaExpress.service.menuInfo.insert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertMenuItemServiceImpl implements IInsertMenuItemService{

    @Autowired
    private PizzaDAO pizzaDAO;

    public String insertMenuItem(String pizzaInfo) {
        PizzaEntity pizzaEntity = PizzaEntity.fromJsonString(pizzaInfo, false);
        JSONObject result = new JSONObject();
        if (pizzaDAO.queryPizzaInfo(pizzaEntity.getP_id()).size() != 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Pizza item exists");
        }
        else {
            pizzaDAO.insert(pizzaEntity);
            if (pizzaDAO.queryPizzaInfo(pizzaEntity.getP_id()).size() == 1)
            {
                result.put("errorCode", 0);
                result.put("p_id", pizzaEntity.getP_id());
                result.put("p_name", pizzaEntity.getP_name());
            }
            else
            {
                result.put("errorCode", 2);
                result.put("errorMsg", "System Error : Insert Error");
            }
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
