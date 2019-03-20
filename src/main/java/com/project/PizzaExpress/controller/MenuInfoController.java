package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.menuInfo.IViewMenuInfoService;
import com.project.PizzaExpress.service.menuInfo.ViewMenuInfoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/menu")
public class MenuInfoController {

    @Resource
    private IViewMenuInfoService menuInfoService = new ViewMenuInfoServiceImpl();

    @RequestMapping("/info")
    public void displayMenu(HttpSession session)
    {
//        System.out.println(menuInfoService.displayMenu());
        session.setAttribute("return", menuInfoService.displayMenu());
    }
}
