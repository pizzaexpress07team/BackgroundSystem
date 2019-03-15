package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.menuInfo.ViewMenuInfoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/menu")
public class MenuInfoController {

    @Resource
    private ViewMenuInfoServiceImpl menuInfoService;

    @RequestMapping("/info")
    public String displayMenu()
    {
//        System.out.println(menuInfoService.displayMenu());
        return menuInfoService.displayMenu();
    }
}
