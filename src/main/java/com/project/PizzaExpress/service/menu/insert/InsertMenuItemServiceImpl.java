package com.project.PizzaExpress.service.menu.insert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.dao.PizzaTypeDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import com.project.PizzaExpress.entity.PizzaTypeEntity;
import com.project.PizzaExpress.entity.PizzaWithResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertMenuItemServiceImpl implements IInsertMenuItemService{

    @Autowired
    private PizzaDAO pizzaDAO;
    @Autowired
    private PizzaTypeDAO pizzaTypeDAO;

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

    public JSONObject insertMenuItemWithRes(String pizzaInfoWithRes){
        JSONObject result = new JSONObject();
        if (pizzaInfoWithRes == null || pizzaInfoWithRes.equals(""))
        {
            result.put("errorCode", 3);
            result.put("errorMsg", "No pizza information");
        } else{
            PizzaWithResEntity pizzaWithResEntity = PizzaWithResEntity.fromJsonString(pizzaInfoWithRes,false);
            PizzaEntity pizzaEntity = PizzaEntity.fromJsonString(pizzaInfoWithRes,false);
            PizzaTypeEntity pizzaTypeEntity = PizzaTypeEntity.fromJsonString(pizzaInfoWithRes);
            if(pizzaWithResEntity==null){
                result.put("errorCode", 4);
                result.put("errorMsg", "Lack of necessary pizza information");
            } else if(pizzaDAO.queryPizzaInfo(pizzaWithResEntity.getP_id()).size() != 0){
                result.put("errorCode", 1);
                result.put("errorMsg", "Pizza item exists");
            }else {
                pizzaDAO.insert(pizzaEntity);
                pizzaTypeDAO.insert(pizzaTypeEntity);
                if(pizzaDAO.queryPizzaInfo(pizzaEntity.getP_id()).size() == 1 &&
                        pizzaTypeDAO.query(pizzaTypeEntity.getP_name()).size() == 1 ){
                    result.put("errorCode", 0);
                    result.put("p_id", pizzaWithResEntity.getP_id());
                    result.put("p_name", pizzaWithResEntity.getP_name());
                    result.put("resource", pizzaWithResEntity.getResource());
                }else{
                    result.put("errorCode", 2);
                    result.put("errorMsg", "System Error : Insert Error");
                }
            }
        }
        return result;
    }
}
