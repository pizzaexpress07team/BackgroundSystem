package com.project.PizzaExpress.service.orderManage.modifyOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.OrderEntity;

public interface IModifyOrderService {
    JSONObject modifyOrderDeliState(Integer deliveryState, String o_id);
}
