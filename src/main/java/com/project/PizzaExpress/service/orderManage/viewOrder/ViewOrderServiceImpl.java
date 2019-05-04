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

    @Override
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

    @Override
    public JSONObject viewOrderByUser(String uid)
    {
        JSONObject result = new JSONObject();
        List<OrderEntity> orders = orderDAO.queryOrders(uid);
        result.put("total", orders.size());
        result.put("list", orders);
        return result;
    }

    @Override
    public JSONObject getAllOrder(Integer pno, Integer pageSize) {
        JSONObject result = new JSONObject();
        int startIndex = (pno - 1) * pageSize;
        List<OrderEntity> list = orderDAO.queryAll(startIndex, pageSize);
        String total = orderDAO.queryOrderSize();
        if(list.size() == 0){
            result.put("errorCode", 1);
            result.put("errorMsg", "No order in this page");
        }else{
            result.put("errorCode", 0);
            result.put("databaseTotal",total);
            result.put("list",list);
        }
        return result;
    }

    @Override
    public JSONObject getAllOrderByTime(Integer pno, Integer pageSize){
        JSONObject result = new JSONObject();
        int startIndex = (pno - 1) * pageSize;
        List<OrderEntity> list = orderDAO.queryAllByTime(startIndex,pageSize);
        String total = orderDAO.queryOrderSize();
        if(list.size() == 0){
            result.put("errorCode", 1);
            result.put("errorMsg", "No order in this page");
        }else{
            result.put("errorCode", 0);
            result.put("databaseTotal",total);
            result.put("list",list);
        }
        return result;
    }

    @Override
    public List<OrderEntity> getOrderStatusByIdLike(String orderId) {
        List<OrderEntity> query = orderDAO.queryLike("%" + orderId + "%");
        return query;
    }

    @Override
    public List<OrderEntity> getOrderStatusById(String orderId){
        List<OrderEntity> query = orderDAO.query(orderId);
        return query;
    }

    @Override
    public JSONObject getOrderStatusByNameLike(String username){
        JSONObject result = new JSONObject();

        return result;
    }
}
