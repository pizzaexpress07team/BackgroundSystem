package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.entity.PizzaEntity;
import com.project.PizzaExpress.entity.PizzaWithResEntity;
import com.project.PizzaExpress.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/message")
    public String test()
    {
        return "Test message.";
    }

    /**
     * 测试保存数据方法.
     * @return running message
     */
    @RequestMapping("/save")
    public String save(){
        PizzaEntity pe = new PizzaEntity();
        pe.setP_name("test");
        testService.save(pe);
        return "ok.TestController.save";

    }

    @RequestMapping("/query")
    public String query(){
        PizzaWithResEntity pe = new PizzaWithResEntity();
        return JSON.toJSONString(pe, SerializerFeature.WriteMapNullValue);

    }
}
