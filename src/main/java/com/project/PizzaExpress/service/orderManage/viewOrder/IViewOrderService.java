package com.project.PizzaExpress.service.orderManage.viewOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.OrderEntity;

import java.util.List;

public interface IViewOrderService {

    JSONObject viewOrder(String o_id);
    JSONObject viewOrderByUser(String uid);

    JSONObject getAllOrder(Integer pno, Integer pageSize);
    JSONObject getAllOrderByTime(Integer pno, Integer pageSize);

    List<OrderEntity> getOrderStatusByIdLike(String orderId);
    List<OrderEntity> getOrderStatusById(String orderId);

    JSONObject getOrderStatusByUserName(String username);
}
