package com.project.PizzaExpress.service.orderManage.viewOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewOrderServiceImpl implements IViewOrderService{

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DeliverymanDAO deliverymanDAO;

    public JSONObject viewOrder(String o_id)
    {
        JSONObject result = new JSONObject();
        List<OrderEntity> orders = orderDAO.query(o_id);
        if (orders.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "No such order");
        }
        else
        {
            result.put("errorCode", 0);
            result.put("order", orders.get(0));
            List<DeliverymanEntity> dms = deliverymanDAO.queryAll();
            int index = (int)(Math.random() * dms.size());
            DeliverymanEntity dm = dms.get(index);
            result.put("deliveryman", dm);
        }
        return result;
    }

    public JSONObject viewOrderByUser(String uid)
    {
        JSONObject result = new JSONObject();
        List<OrderEntity> orders = orderDAO.queryOrders(uid);
        result.put("total", orders.size());
        result.put("list", orders);
        return result;
    }
}
