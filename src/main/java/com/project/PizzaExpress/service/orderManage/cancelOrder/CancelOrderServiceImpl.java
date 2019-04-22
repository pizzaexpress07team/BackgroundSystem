package com.project.PizzaExpress.service.orderManage.cancelOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelOrderServiceImpl implements ICancelOrderService{

    @Autowired
    private OrderDAO orderDAO;

    public JSONObject cancelOrder(String o_id)
    {
        JSONObject result = new JSONObject();
        orderDAO.delete(o_id);
        result.put("errorCode", 0);
        return result;
    }
}
