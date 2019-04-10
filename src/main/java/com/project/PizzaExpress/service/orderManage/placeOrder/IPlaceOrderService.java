package com.project.PizzaExpress.service.orderManage.placeOrder;

import com.alibaba.fastjson.JSONObject;

public interface IPlaceOrderService {

    JSONObject confirmOrder(String orderInfo, String addrID);
    JSONObject payOrder(String o_id);
}
