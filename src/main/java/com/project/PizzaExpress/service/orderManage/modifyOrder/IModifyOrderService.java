package com.project.PizzaExpress.service.orderManage.modifyOrder;

import com.project.PizzaExpress.entity.OrderEntity;

public interface IModifyOrderService {
    OrderEntity modifyOrderStatus(Integer status, String orderId);
}
