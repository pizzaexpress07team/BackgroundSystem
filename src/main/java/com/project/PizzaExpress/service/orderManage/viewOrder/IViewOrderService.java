package com.project.PizzaExpress.service.orderManage.viewOrder;

import com.alibaba.fastjson.JSONObject;

public interface IViewOrderService {

    JSONObject viewOrder(String o_id);

    JSONObject viewOrderByUser(String uid);
}
