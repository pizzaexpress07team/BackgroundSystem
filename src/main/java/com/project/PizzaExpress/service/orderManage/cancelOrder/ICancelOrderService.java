package com.project.PizzaExpress.service.orderManage.cancelOrder;

import com.alibaba.fastjson.JSONObject;

public interface ICancelOrderService {

    JSONObject cancelOrder(String o_id);
    JSONObject drawBack(String o_id); //退款
}
