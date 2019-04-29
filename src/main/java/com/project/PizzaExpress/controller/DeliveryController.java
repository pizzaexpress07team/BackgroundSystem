package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.service.deliverymanManage.update.IUpdateDeliverymanInfoService;
import com.project.PizzaExpress.service.deliverymanManage.update.UpdateDeliManImpl;
import com.project.PizzaExpress.service.deliverymanManage.view.IViewDeliverymanInfoService;
import com.project.PizzaExpress.service.deliverymanManage.view.ViewDeliverymanInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/deli")
@CrossOrigin
public class DeliveryController {

    @Resource
    private IUpdateDeliverymanInfoService updateDeliverymanInfoService = new UpdateDeliManImpl();
    @Resource
    private IViewDeliverymanInfoService viewDeliverymanInfoService = new ViewDeliverymanInfoServiceImpl();

    @RequestMapping("/locate")
    public String locate(HttpSession session, @RequestBody String info) {
        String re = JSON.toJSONString(updateDeliverymanInfoService.updateLocation(info));
        session.setAttribute("return", re);
        return re;
    }

    //精准查找
    @RequestMapping("/status")
    public String getDeliverStatus(@RequestParam String deliverId) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDeliverStatus(deliverId));
    }

    //模糊查找
    @RequestMapping("/get")
    public String getDeliverStatusLike(@RequestParam String d_name) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDeliverStatusLike(d_name));
    }

    //显示所有配送员信息
    @RequestMapping("/OldList")
    public String getAllDeliver() {
        return JSON.toJSONString(viewDeliverymanInfoService.viewAllInfo());
    }

    //分页显示所有配送员信息
    @RequestMapping("/list")
    public String getAllDeliverByPage(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(viewDeliverymanInfoService.getAllDeliveryManByPage(pno, pageSize));
    }

    //增加配送员信息TODO
    @RequestMapping("/add")
    public String addDeliverMan(@RequestParam String deliverInfo){
        DeliverymanEntity deliverymanEntity = DeliverymanEntity.fromJsonString(deliverInfo,false);
        return null;
    }

    //分页显示所有当前有订单的配送员信息以及订单信息
    @RequestMapping("/listOrder")
    public String getAllDeliverOrderByPage(@RequestParam Integer pno, @RequestParam Integer pageSize){
        return JSON.toJSONString(viewDeliverymanInfoService.getAllDeliveryOrderByPage(pno,pageSize));
    }
}
