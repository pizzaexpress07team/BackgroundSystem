package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
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

    @RequestMapping("/status")
    public String getDeliverStatus(@RequestParam String deliverId) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDeliverStatus(deliverId));
    }

    //显示所有配送员信息
    @RequestMapping("/OldList")
    public String getAllDeliver() {
        return JSON.toJSONString(viewDeliverymanInfoService.viewAllInfo());
    }

    //分页显示所有配送员信息
    @RequestMapping("/list")
    public String getAllOrder(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(viewDeliverymanInfoService.getAllDeliveryManByPage(pno, pageSize));
    }
}
