package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.service.deliverymanManage.updateInfo.IUpdateDeliverymanInfoService;
import com.project.PizzaExpress.service.deliverymanManage.updateInfo.UpdateDeliManImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/deli")
@CrossOrigin
public class DeliveryController {

    @Resource
    private IUpdateDeliverymanInfoService updateDeliverymanInfoService = new UpdateDeliManImpl();

    @RequestMapping("/locate")
    public String locate(HttpSession session, @RequestBody String info) {
        String re = JSON.toJSONString(updateDeliverymanInfoService.updateLocation(info));
        session.setAttribute("return", re);
        return re;
    }
}
