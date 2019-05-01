package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.service.deliverymanManage.join.CreateNewDeliverymanServiceImpl;
import com.project.PizzaExpress.service.deliverymanManage.join.ICreateNewDeliverymanService;
import com.project.PizzaExpress.service.deliverymanManage.quit.DeleteDeliverymanServiceImpl;
import com.project.PizzaExpress.service.deliverymanManage.quit.IDeleteDeliverymanService;
import com.project.PizzaExpress.service.deliverymanManage.update.IUpdateDeliverymanInfoService;
import com.project.PizzaExpress.service.deliverymanManage.update.UpdateDeliManImpl;
import com.project.PizzaExpress.service.deliverymanManage.view.IViewDeliverymanInfoService;
import com.project.PizzaExpress.service.deliverymanManage.view.ViewDeliverymanInfoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/deli")
@CrossOrigin
public class DeliveryController {

    @Resource
    private IUpdateDeliverymanInfoService updateDeliverymanInfoService = new UpdateDeliManImpl();
    @Resource
    private IViewDeliverymanInfoService viewDeliverymanInfoService = new ViewDeliverymanInfoServiceImpl();
    @Resource
    private ICreateNewDeliverymanService createNewDeliverymanService = new CreateNewDeliverymanServiceImpl();
    @Resource
    private IDeleteDeliverymanService deleteDeliverymanService = new DeleteDeliverymanServiceImpl();

    @RequestMapping("/locate")
    public String locate(HttpSession session, @RequestBody String info) {
        String re = JSON.toJSONString(updateDeliverymanInfoService.updateLocation(info));
        session.setAttribute("return", re);
        return re;
    }

    //根据d_id查找配送员信息（精准）
    @RequestMapping("/getById")
    public String getDelivermanById(@RequestParam String deliverId) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDelivermanById(deliverId));
    }

    //根据d_id查找配送员信息（模糊）
    @RequestMapping("/getByIdLike")
    public String getDelivermanByIdLike(@RequestParam String deliverId) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDelivermanByIdLike(deliverId));
    }

    //根据d_name查找配送员信息（模糊）
    @RequestMapping("/getByNameLike")
    public String getDeliverStatusLike(@RequestParam String d_name) {
        return JSON.toJSONString(viewDeliverymanInfoService.getDelivermanByNameLike(d_name));
    }

    //显示所有配送员信息
    @RequestMapping("/OldList")
    public String getAllDeliver() {
        return JSON.toJSONString(viewDeliverymanInfoService.viewAllInfo());
    }

    //分页显示所有配送员信息
    @RequestMapping("/list")
    public String getAllDeliverByPage(@RequestParam Integer pno, @RequestParam Integer pageSize) {
        return JSON.toJSONString(viewDeliverymanInfoService.getAllDeliveryManByPage(pno, pageSize));
    }

    //分页显示所有当前有订单的配送员信息以及订单信息
    @RequestMapping("/listOrder")
    public String getAllDeliverOrderByPage(@RequestParam Integer pno, @RequestParam Integer pageSize){
        return JSON.toJSONString(viewDeliverymanInfoService.getAllDeliveryOrderByPage(pno,pageSize));
    }

    //增加配送员信息
    @RequestMapping("/add")
    public String addDeliverMan(@RequestParam String deliverInfo){
        return JSON.toJSONString(createNewDeliverymanService.insertDeliveryman(deliverInfo));
    }

    //删除配送员信息
    @RequestMapping("/delete")
    public String deleteDeliverMan(@RequestParam String d_id){
        return JSON.toJSONString(deleteDeliverymanService.deleteDeliveryman(d_id));
    }

    //修改配送员信息
    @RequestMapping("/modify")
    public String modifyDeliverMan(@RequestParam String deliverInfo){
        return JSON.toJSONString(updateDeliverymanInfoService.modifyDeliveryman(deliverInfo));
    }
}
