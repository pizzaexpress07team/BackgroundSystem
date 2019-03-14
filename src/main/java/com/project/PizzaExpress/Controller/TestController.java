package com.project.PizzaExpress.Controller;

import com.project.PizzaExpress.Entity.PizzaEntity;
import com.project.PizzaExpress.Service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    /**
     * 测试保存数据方法.
     * @return running message
     */
    @RequestMapping("/save")
    public String save(){
        PizzaEntity pe = new PizzaEntity();
        pe.setName("test");
        testService.save(pe);//保存数据.
        return "ok.TestController.save";

    }
}
