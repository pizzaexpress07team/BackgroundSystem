package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.service.orderManage.placeOrder.IPlaceOrderService;
import com.project.PizzaExpress.service.orderManage.placeOrder.PlaceOrderServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Resource
    private IPlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    @RequestMapping("/confirm")
    public String confirmOrder(String orderInfo, int addrID) {
        JSONObject re = placeOrderService.confirmOrder(orderInfo, addrID);
        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/list")
    public String getAllOrder(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(placeOrderService.getAllOrder(pno, pageSize));
    }

    @RequestMapping("/status/modify")
    public String modifyOrderStatus(@RequestParam Integer status,@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.modifyOrderStatus(status, orderId));
    }

    @RequestMapping("/status/delete")
    public String deleteOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.deleteOrderStatus(orderId));
    }

    @RequestMapping("/status/get")
    public String getOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.getOrderStatus(orderId));
    }
}
