package com.project.PizzaExpress.service.deliverymanManage.view;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.DeliManWithOrderEntity;
import com.project.PizzaExpress.entity.DeliverymanEntity;

import java.util.List;


public interface IViewDeliverymanInfoService {

    JSONObject viewInfo(String d_id);
    JSONObject viewAllInfo();
    JSONObject viewByFactory(String f_id);

    DeliverymanEntity getDeliverStatus(String deliverId);
    List<DeliverymanEntity> getDeliverStatusLike(String d_name);

    JSONObject getAllDeliveryManByPage(Integer pno, Integer pageSize);
    JSONObject getAllDeliveryOrderByPage(Integer pno, Integer pageSize);
}
