package com.project.PizzaExpress.service.orderManage.payOrder;

import com.alibaba.fastjson.JSONObject;

public interface IPayOrderService {
    JSONObject payOrder(String o_id);
}
