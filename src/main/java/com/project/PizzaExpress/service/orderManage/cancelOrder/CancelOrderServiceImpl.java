package com.project.PizzaExpress.service.orderManage.cancelOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CancelOrderServiceImpl implements ICancelOrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public JSONObject cancelOrder(String o_id)
    {
        JSONObject result = new JSONObject();
        List<OrderEntity> query = orderDAO.query(o_id);
        if(ObjectUtils.isEmpty(query)){
            result.put("errorCode", 1);
            result.put("errorMsg", "No such order ID");
        }else{
            orderDAO.delete(o_id);
            if(orderDAO.query(o_id).size() == 0){
                result.put("errorCode", 0);
                result.put("successCancel",query.get(0));
            }else{
                result.put("errorCode", 2);
                result.put("errorMsg","System Error : Delete Error");
            }
            if(query.get(0).getO_pay_state() == 1){
                drawBack(o_id); //如果已经付款，则退款
            }
        }
        return result;
    }

    //退款
    @Override
    public JSONObject drawBack(String o_id){
        return null;
    }
}
