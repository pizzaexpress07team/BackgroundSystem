package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.service.FactoryRes.FactoryResServiceImpl;
import com.project.PizzaExpress.service.FactoryRes.IFactoryResService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/FactoryRes")
@CrossOrigin
public class FactoryResController {
    @Resource
    private IFactoryResService factoryResService = new FactoryResServiceImpl();

    @RequestMapping("/list")
    public String getAllFactoryRes() {
        return JSON.toJSONString(factoryResService.getAllFactoryRes());
    }

    @RequestMapping("/get")
    public String getFactoryRes(@RequestParam String f_id) {
        return JSON.toJSONString(factoryResService.getFactoryRes(f_id));
    }
}
