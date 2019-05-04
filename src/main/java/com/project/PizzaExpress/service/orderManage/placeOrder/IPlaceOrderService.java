package com.project.PizzaExpress.service.orderManage.placeOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.OrderEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface IPlaceOrderService {

    JSONObject confirmOrder(String orderInfo);
    JSONObject payOrder(String o_id);

    JSONObject getAllOrder(Integer pno, Integer pageSize);
    JSONObject getAllOrderByTime(Integer pno, Integer pageSize);

    OrderEntity modifyOrderStatus(Integer status, String orderId);

    JSONObject deleteOrderStatus(String orderId);

    List<OrderEntity> getOrderStatus(String orderId);

    List<OrderEntity> queryOrderStatus(String orderId);
}
