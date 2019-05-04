package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.service.orderManage.cancelOrder.CancelOrderServiceImpl;
import com.project.PizzaExpress.service.orderManage.cancelOrder.ICancelOrderService;
import com.project.PizzaExpress.service.orderManage.deleteOrder.DeleteOrderServiceImpl;
import com.project.PizzaExpress.service.orderManage.deleteOrder.IDeleteOrderService;
import com.project.PizzaExpress.service.orderManage.modifyOrder.IModifyOrderService;
import com.project.PizzaExpress.service.orderManage.modifyOrder.ModifyOrderServiceImpl;
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
    private IDeleteOrderService deleteOrderService = new DeleteOrderServiceImpl();
    @Resource
    private IModifyOrderService modifyOrderService = new ModifyOrderServiceImpl();
    @Resource
    private ICancelOrderService cancelOrderService = new CancelOrderServiceImpl();

    //确认订单
    @RequestMapping("/confirm")
    public String confirmOrder(@RequestBody String orderInfo) {
        JSONObject re = placeOrderService.confirmOrder(orderInfo);
        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }

    //支付订单
    @RequestMapping("/pay")
    public String payOrder(@RequestParam(name = "o_id") String o_id) {
        JSONObject re = payOrderService.payOrder(o_id);
        return JSON.toJSONString(re, SerializerFeature.WriteMapNullValue);
    }

    //分页查看所有订单
    @RequestMapping("/list")
    public String getAllOrder(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(viewOrderService.getAllOrder(pno, pageSize));
    }

    //分页查看所有订单（按时间排序）
    @RequestMapping("/listByTime")
    public String getAllOrderByTime(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(viewOrderService.getAllOrderByTime(pno, pageSize));
    }

    //弃用
    @RequestMapping("/query")
    public String viewOrder(@RequestParam(name = "o_id") String o_id) {
        return JSON.toJSONString(viewOrderService.viewOrder(o_id));
    }

    //根据订单ID查询订单（精准）
    @RequestMapping("/status/getById")
    public String getOrderStatusById(@RequestParam String orderId) {
        return JSON.toJSONString(viewOrderService.getOrderStatusById(orderId));
    }

    //根据订单ID查询订单（模糊）
    @RequestMapping("/status/getByIdLike")
    public String getOrderStatusByIdLike(@RequestParam String orderId) {
        return JSON.toJSONString(viewOrderService.getOrderStatusByIdLike(orderId));
    }

    //根据用户名查询订单（精准）（注：这里的用户名是用户账户名，非配送客户名）
    @RequestMapping("/status/getByUserName")
    public String getOrderStatusByNameLike(@RequestParam String username) {
        return JSON.toJSONString(viewOrderService.getOrderStatusByUserName(username));
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
        return JSON.toJSONString(modifyOrderService.modifyOrderStatus(status, orderId));
    }

    //删除订单
    @RequestMapping("/status/delete")
    public String deleteOrderStatus(@RequestParam String orderId) {
        return JSON.toJSONString(deleteOrderService.deleteOrderStatus(orderId));
    }
}
