package com.project.PizzaExpress.service.menu.modify;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.dao.PizzaTypeDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import com.project.PizzaExpress.entity.PizzaTypeEntity;
import com.project.PizzaExpress.entity.PizzaWithResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyMenuItemServiceImpl implements IModifyMenuItemService{
    @Autowired
    private PizzaDAO pizzaDAO;
    @Autowired
    private PizzaTypeDAO pizzaTypeDAO;

    public JSONObject modifyMenuItemWithRes(String pizzaInfoWithRes){
        JSONObject result = new JSONObject();
        if (pizzaInfoWithRes == null || pizzaInfoWithRes.equals("")) {
            result.put("errorCode", 3);
            result.put("errorMsg", "No pizza information");
        }else{
            PizzaWithResEntity pizzaWithResEntity = PizzaWithResEntity.fromJsonString(pizzaInfoWithRes,true);
            PizzaEntity pizzaEntity = PizzaEntity.fromJsonString(pizzaInfoWithRes,true);
            PizzaTypeEntity pizzaTypeEntity = PizzaTypeEntity.fromJsonString(pizzaInfoWithRes);
            if(pizzaWithResEntity==null){
                result.put("errorCode", 4);
                result.put("errorMsg", "Lack of necessary pizza information");
            }else if(pizzaDAO.queryPizzaInfo(pizzaWithResEntity.getP_id()).size() == 0){
                result.put("errorCode", 1);
                result.put("errorMsg", "Pizza item not exists");
            }else{
                pizzaDAO.updatePizzaInfo(pizzaEntity);
                pizzaTypeDAO.updatePizzaType(pizzaTypeEntity);
                result.put("errorCode", 0);
                result.put("successModify", pizzaWithResEntity);
            }
        }
        return result;
    }
}
