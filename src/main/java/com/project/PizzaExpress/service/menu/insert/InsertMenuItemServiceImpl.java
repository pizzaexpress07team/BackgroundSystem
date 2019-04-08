package com.project.PizzaExpress.service.menu.insert;

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
        JSONObject result = new JSONObject();
        if (pizzaInfo == null || pizzaInfo.equals(""))
        {
            result.put("errorCode", 3);
            result.put("errorMsg", "No pizza information");
        }
        else {
            PizzaEntity pizzaEntity = PizzaEntity.fromJsonString(pizzaInfo, false);
            if (pizzaEntity == null)
            {
                result.put("errorCode", 4);
                result.put("errorMsg", "Lack of necessary pizza information");
            }
            else if (pizzaDAO.queryPizzaInfo(pizzaEntity.getP_id()).size() != 0) {
                result.put("errorCode", 1);
                result.put("errorMsg", "Pizza item exists");
            } else {
                pizzaDAO.insert(pizzaEntity);
                if (pizzaDAO.queryPizzaInfo(pizzaEntity.getP_id()).size() == 1) {
                    result.put("errorCode", 0);
                    result.put("p_id", pizzaEntity.getP_id());
                    result.put("p_name", pizzaEntity.getP_name());
                } else {
                    result.put("errorCode", 2);
                    result.put("errorMsg", "System Error : Insert Error");
                }
            }
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
