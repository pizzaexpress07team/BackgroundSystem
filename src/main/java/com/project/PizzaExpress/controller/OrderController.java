package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.service.orderManage.placeOrder.IPlaceOrderService;
import com.project.PizzaExpress.service.orderManage.placeOrder.PlaceOrderServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Resource
    private IPlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    @RequestMapping("/confirm")
    public String confirmOrder(String orderInfo, int addrID)
    {
        JSONObject re = placeOrderService.confirmOrder(orderInfo, addrID);

        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }
}
