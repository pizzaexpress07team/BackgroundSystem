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

    //查询所有工厂库存信息
    @RequestMapping("/oldList")
    public String getAllFactoryRes() {
        return JSON.toJSONString(factoryResService.getAllFactoryRes());
    }

    //查询所有工厂库存信息（分页）
    @RequestMapping("/list")
    public String getAllFactoryResByPage(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(factoryResService.getAllFactoryResByPage(pno, pageSize));
    }

    //根据工厂id 查询工厂库存信息（精准）
    @RequestMapping("/getByFId")
    public String getFactoryResByFId(@RequestParam String f_id) {
        return JSON.toJSONString(factoryResService.getFactoryResByFId(f_id));
    }

    //根据工厂id 查询工厂库存信息（模糊）
    @RequestMapping("/getByFIdLike")
    public String getFactoryResByFIdLike(@RequestParam String f_id) {
        return JSON.toJSONString(factoryResService.getFactoryResByFIdLike(f_id));
    }

    //根据原料名 查询工厂库存信息（精准）
    @RequestMapping("/getByRName")
    public String getFactoryResByRName(@RequestParam String r_name) {
        return JSON.toJSONString(factoryResService.getFactoryResByRName(r_name));
    }

    //根据原料名 查询工厂库存信息（模糊）
    @RequestMapping("/getByRNameLike")
    public String getFactoryResByRNameLike(@RequestParam String r_name) {
        return JSON.toJSONString(factoryResService.getFactoryResByRNameLike(r_name));
    }

    //根据原料id 查询工厂库存信息（精准）
    @RequestMapping("/getByRId")
    public String getFactoryResByRId(@RequestParam String r_id) {
        return JSON.toJSONString(factoryResService.getFactoryResByRId(r_id));
    }

    //根据工厂id和原料id 查询工厂库存信息（精准）

    //修改工厂库存数量
    @RequestMapping("/updateResNum")
    public String updateFactoryResNum(@RequestParam String f_id,@RequestParam String r_id,@RequestParam int num) {
        return JSON.toJSONString(factoryResService.updateFactoryResNum(f_id,r_id,num));
    }

    //增加工厂库存信息条目
    @RequestMapping("/addFacResItem")
    public String addFactoryResItem(@RequestParam String FacResInfo) {
        return JSON.toJSONString(factoryResService.addFactoryResItem(FacResInfo));
    }
}
