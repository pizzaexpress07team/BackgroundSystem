package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.menuInfo.insert.IInsertMenuItemService;
import com.project.PizzaExpress.service.menuInfo.insert.InsertMenuItemServiceImpl;
import com.project.PizzaExpress.service.menuInfo.view.IViewMenuInfoService;
import com.project.PizzaExpress.service.menuInfo.view.ViewMenuInfoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        String result = menuInfoService.displayMenu();
//        request.setAttribute("return", result);
        return result;
    }

    @RequestMapping("/create")
    public String createNewMenuItem(@RequestBody String pizzaInfo)
    {
        String re = insertMenuItemService.insertMenuItem(pizzaInfo);
        return re;
    }
}
