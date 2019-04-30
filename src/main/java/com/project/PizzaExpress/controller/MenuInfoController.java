package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.service.menu.insert.IInsertMenuItemService;
import com.project.PizzaExpress.service.menu.insert.InsertMenuItemServiceImpl;
import com.project.PizzaExpress.service.menu.modify.IModifyMenuItemService;
import com.project.PizzaExpress.service.menu.modify.ModifyMenuItemServiceImpl;
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
    @Resource
    private IModifyMenuItemService modifyMenuItemService = new ModifyMenuItemServiceImpl();

    //查看菜单中的所有菜单项（带原料）
    @RequestMapping("/info")
    public String displayMenu(HttpServletRequest request)
    {
//        System.out.println(menuInfoService.displayMenu());
        String result = JSON.toJSONString(menuInfoService.totalDisplay());
//        request.setAttribute("return", result);
        return result;
    }

    //新建一个菜单项（不带原料）
    @RequestMapping("/create")
    public String createNewMenuItem(@RequestBody String pizzaInfo)
    {
        String re = insertMenuItemService.insertMenuItem(pizzaInfo);
        return re;
    }

    //新建一个菜单项（带原料）
    @RequestMapping("/createWithRes")
    public String createNewMenuItemWithRes(@RequestBody String pizzaInfoWithRes){
        return JSON.toJSONString(insertMenuItemService.insertMenuItemWithRes(pizzaInfoWithRes));
    }

    //根据披萨名 模糊查询披萨信息（带原料）
    @RequestMapping("/getByNameLike")
    public String getItemWithResByNameLike(@RequestParam String p_name){
        return JSON.toJSONString(menuInfoService.getItemWithResByNameLike(p_name));
    }

    //根据披萨id 模糊查询披萨信息（带原料）
    @RequestMapping("/getByIdLike")
    public String getItemWithResByIdLike(@RequestParam String p_name){
        //TODO
        return null;
    }

    //根据披萨id 精准查询披萨信息（带原料）

    //修改一个菜单项（带原料）
    @RequestMapping("/modify")
    public String modifyMenuItemWithRes(@RequestBody String pizzaInfoWithRes){
        return JSON.toJSONString(modifyMenuItemService.modifyMenuItemWithRes(pizzaInfoWithRes));
    }
}
