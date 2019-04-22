package com.project.PizzaExpress.service.stockManage.resource;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.OrderEntity;

public interface IResourceManageService {

    JSONObject decreaseResource(OrderEntity order);
}
