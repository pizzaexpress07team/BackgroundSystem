package com.project.PizzaExpress.service.deliverymanManage.view;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.DeliverymanEntity;

import java.util.List;


public interface IViewDeliverymanInfoService {

    JSONObject viewInfo(String d_id);
    JSONObject viewAllInfo();
    JSONObject viewByFactory(String f_id);

    DeliverymanEntity getDeliverStatus(String deliverId);

    List<DeliverymanEntity> getAllDeliveryManByPage(Integer pno, Integer pageSize);
}
