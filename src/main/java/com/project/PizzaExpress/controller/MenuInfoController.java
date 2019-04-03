package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.menuInfo.IViewMenuInfoService;
import com.project.PizzaExpress.service.menuInfo.ViewMenuInfoServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/menu")
public class MenuInfoController {

    @Resource
    private IViewMenuInfoService menuInfoService = new ViewMenuInfoServiceImpl();

    @RequestMapping("/info")
    public void displayMenu(HttpServletRequest request)
    {
//        System.out.println(menuInfoService.displayMenu());
        String result = menuInfoService.displayMenu();
        request.setAttribute("return", result);
        //return result;
    }
}
