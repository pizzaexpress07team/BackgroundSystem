package com.project.PizzaExpress.service.deliverymanManage.join;

import com.alibaba.fastjson.JSONObject;

public interface ICreateNewDeliverymanService {

    JSONObject joinDeliveryman(String uid, String f_id);
    JSONObject insertDeliveryman(String deliverInfo);
}
