package com.project.PizzaExpress.service.menu.delete;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.dao.PizzaTypeDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DeleteMenuItemServiceImpl implements IDeleteMenuItemService{
    @Autowired
    private PizzaDAO pizzaDAO;
    @Autowired
    private PizzaTypeDAO pizzaTypeDAO;

    public JSONObject deleteMenuItemWithRes(String p_id){
        JSONObject result = new JSONObject();
        List<PizzaEntity> query = pizzaDAO.queryPizzaInfo(p_id);
        if(ObjectUtils.isEmpty(query)){
            result.put("errorCode", 1);
            result.put("errorMsg", "No such menu item");
        }else {
            pizzaTypeDAO.delete(query.get(0).getP_name());
            pizzaDAO.delete(p_id);
            result.put("errorCode",0);
            result.put("deletedItem",query);
        }
        return result;
    }
}
