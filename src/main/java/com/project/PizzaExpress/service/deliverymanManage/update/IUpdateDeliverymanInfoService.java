package com.project.PizzaExpress.service.deliverymanManage.update;

import com.alibaba.fastjson.JSONObject;

public interface IUpdateDeliverymanInfoService {

    JSONObject updateLocation(String info);
    JSONObject modifyDeliveryman(String deliverInfo);
}
