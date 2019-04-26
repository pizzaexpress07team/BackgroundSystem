package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.service.menu.insert.IInsertMenuItemService;
import com.project.PizzaExpress.service.menu.insert.InsertMenuItemServiceImpl;
import com.project.PizzaExpress.service.menu.view.IViewMenuInfoService;
import com.project.PizzaExpress.service.menu.view.ViewMenuInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/menu")
@CrossOrigin
public class MenuInfoController {

    @Resource
    private IViewMenuInfoService menuInfoService = new ViewMenuInfoServiceImpl();
    @Resource
    private IInsertMenuItemService insertMenuItemService = new InsertMenuItemServiceImpl();

    @RequestMapping("/info")
    public String displayMenu(HttpServletRequest request)
    {
//        System.out.println(menuInfoService.displayMenu());
        String result = JSON.toJSONString(menuInfoService.totalDisplay());
//        request.setAttribute("return", result);
        return result;
    }

    @RequestMapping("/create")
    public String createNewMenuItem(@RequestBody String pizzaInfo)
    {
        String re = insertMenuItemService.insertMenuItem(pizzaInfo);
        return re;
    }

    @RequestMapping("/get")
    public String getMenuStatus(@RequestParam String p_name){
        return JSON.toJSONString(menuInfoService.getMenuStatus(p_name));
    }
}
