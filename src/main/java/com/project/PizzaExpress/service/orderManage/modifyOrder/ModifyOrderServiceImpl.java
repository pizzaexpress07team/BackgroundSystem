package com.project.PizzaExpress.service.orderManage.modifyOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ModifyOrderServiceImpl implements IModifyOrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public JSONObject modifyOrderDeliState(Integer deliveryState, String o_id) {
        JSONObject result = new JSONObject();
        List<OrderEntity> query = orderDAO.query(o_id);
        if (ObjectUtils.isEmpty(query)){
            result.put("errorCode",1);
            result.put("errorMsg","No such order ID");
        }else {
            orderDAO.updateDeliveryState(o_id, deliveryState);
            if(orderDAO.queryDeliStateByOid(o_id).get(0) == deliveryState){
                List<OrderEntity> newQuery = orderDAO.query(o_id);
                result.put("errorCode",0);
                result.put("successModify",newQuery.get(0));
            }else{
                result.put("errorCode",2);
                result.put("errorMsg","System Error : Modify Error");
            }
        }
        return result;
    }
}
