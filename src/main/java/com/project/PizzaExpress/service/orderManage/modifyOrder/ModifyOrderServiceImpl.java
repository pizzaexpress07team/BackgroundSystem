package com.project.PizzaExpress.service.orderManage.modifyOrder;

import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ModifyOrderServiceImpl implements IModifyOrderService{

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public OrderEntity modifyOrderStatus(Integer status, String orderId) {
        List<OrderEntity> query = orderDAO.query(orderId);
        if (ObjectUtils.isEmpty(query)) return null;
        orderDAO.updateDeliveryState(orderId, status);
        return orderDAO.query(orderId).get(0);
    }
}
