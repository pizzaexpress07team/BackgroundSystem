package com.project.PizzaExpress.service.orderManage.deleteOrder;

import com.alibaba.fastjson.JSONObject;

public interface IDeleteOrderService {
    JSONObject deleteOrderStatus(String orderId);
}
