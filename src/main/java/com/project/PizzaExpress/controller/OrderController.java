package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.service.orderManage.cancelOrder.CancelOrderServiceImpl;
import com.project.PizzaExpress.service.orderManage.cancelOrder.ICancelOrderService;
import com.project.PizzaExpress.service.orderManage.payOrder.IPayOrderService;
import com.project.PizzaExpress.service.orderManage.payOrder.PayOrderServiceImpl;
import com.project.PizzaExpress.service.orderManage.placeOrder.IPlaceOrderService;
import com.project.PizzaExpress.service.orderManage.placeOrder.PlaceOrderServiceImpl;
import com.project.PizzaExpress.service.orderManage.viewOrder.IViewOrderService;
import com.project.PizzaExpress.service.orderManage.viewOrder.ViewOrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Resource
    private IPlaceOrderService placeOrderService = new PlaceOrderServiceImpl();
    @Resource
    private IPayOrderService payOrderService = new PayOrderServiceImpl();
    @Resource
    private IViewOrderService viewOrderService = new ViewOrderServiceImpl();
    @Resource
    private ICancelOrderService cancelOrderService = new CancelOrderServiceImpl();

    @RequestMapping("/confirm")
    public String confirmOrder(@RequestBody String orderInfo) {
        JSONObject re = placeOrderService.confirmOrder(orderInfo);
        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/pay")
    public String payOrder(@RequestParam(name = "o_id") String o_id) {
        JSONObject re = payOrderService.payOrder(o_id);
        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/list")
    public String getAllOrder(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(placeOrderService.getAllOrder(pno, pageSize));
    }

    @RequestMapping("/query")
    public String viewOrder(@RequestParam(name = "o_id") String o_id) {
        return JSON.toJSONString(viewOrderService.viewOrder(o_id));
    }

    //取消订单
    @RequestMapping("/cancel")
    public String cancelOrder(@RequestParam(name = "o_id") String o_id) {
        return JSON.toJSONString(cancelOrderService.cancelOrder(o_id));
    }

    //用户查看自己的订单
    @RequestMapping("/view")
    public String viewOrderByUser(@RequestParam(name = "uid") String uid) {
        return JSON.toJSONString(viewOrderService.viewOrderByUser(uid));
    }

    //修改订单状态
    @RequestMapping("/status/modify")
    public String modifyOrderStatus(@RequestParam Integer status,@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.modifyOrderStatus(status, orderId));
    }

    //删除订单
    @RequestMapping("/status/delete")
    public String deleteOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.deleteOrderStatus(orderId));
    }

    //模糊查询订单
    @RequestMapping("/status/get")
    public String getOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.getOrderStatus(orderId));
    }

    //精准查询订单
    @RequestMapping("/status/query")
    public String queryOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(placeOrderService.queryOrderStatus(orderId));
    }
}
