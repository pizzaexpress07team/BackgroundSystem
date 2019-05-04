package com.project.PizzaExpress.service.orderManage.deleteOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DeleteOrderServiceImpl implements IDeleteOrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public JSONObject deleteOrderStatus(String orderId) {
        JSONObject result = new JSONObject();
        List<OrderEntity> query = orderDAO.query(orderId);
        if (ObjectUtils.isEmpty(query)) {
            result.put("errorCode", 1);
            result.put("errorMsg", "No such order id");
        } else {
            orderDAO.delete(orderId);
            result.put("errorCode", 0);
        }
        return result;
    }
}
