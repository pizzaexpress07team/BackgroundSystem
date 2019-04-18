package com.project.PizzaExpress.service.orderManage.placeOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.OrderEntity;

import java.util.List;

public interface IPlaceOrderService {

    JSONObject confirmOrder(String orderInfo, int addrID);
    JSONObject payOrder(String o_id);

    List<OrderEntity> getAllOrder(Integer pno, Integer pageSize);

    OrderEntity modifyOrderStatus(Integer status, String orderId);
}
