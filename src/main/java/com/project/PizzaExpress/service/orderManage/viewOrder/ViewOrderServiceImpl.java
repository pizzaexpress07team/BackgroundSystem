package com.project.PizzaExpress.service.orderManage.viewOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.dao.UserDAO;
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
    private UserDAO userDAO;
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
        List<OrderEntity> list = orderDAO.queryOrders(uid);
        if(list.size() == 0){
            result.put("errorCode", 1);
            result.put("errorMsg", "This uid has no order");
        }else {
            result.put("errorCode",0);
            result.put("list", list);
            result.put("total", list.size());
        }
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
    public JSONObject getOrderStatusById(String o_id){
        JSONObject result = new JSONObject();
        List<OrderEntity> query = orderDAO.query(o_id);
        if(query.size() == 0){
            result.put("errorCode",1);
            result.put("errorMsg","This order ID is not exist");
        }else {
            result.put("errorCode",0);
            result.put("successQuery",query.get(0));
        }
        return result;
    }

    @Override
    public JSONObject getOrderStatusByIdLike(String o_id) {
        JSONObject result = new JSONObject();
        List<OrderEntity> list = orderDAO.queryLike("%" + o_id + "%");
        if(list.size() == 0){
            result.put("errorCode",1);
            result.put("errorMsg","This order ID is not exist");
        }else {
            result.put("errorCode",0);
            result.put("list",list);
            result.put("total",list.size());
        }
        return result;
    }

    @Override
    public JSONObject getOrderStatusByUserName(String username){
        JSONObject result = new JSONObject();
        List<String> uidQuery = userDAO.queryUidByUserName(username);
        if(uidQuery.size()==0){
            result.put("errorCode",1);
            result.put("errorMsg","This username is not exist");
        }else {
            List<OrderEntity> list = orderDAO.queryOrderByUid(uidQuery.get(0));
            if(list.size() == 0){
                result.put("errorCode",2);
                result.put("errorMsg","This username have no order");
            }else{
                result.put("errorCode",0);
                result.put("total",list.size());
                result.put("list",list);
            }
        }
        return result;
    }
}
