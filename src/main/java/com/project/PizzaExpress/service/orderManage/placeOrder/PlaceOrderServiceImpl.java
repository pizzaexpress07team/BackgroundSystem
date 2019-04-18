package com.project.PizzaExpress.service.orderManage.placeOrder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.service.oAlloc.simpleAlloc.SimpleOrderAlloc;
import com.project.PizzaExpress.service.orderManage.withUser.GetUserAddr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PlaceOrderServiceImpl implements IPlaceOrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Resource
    private SimpleOrderAlloc simpleOrderAlloc = new SimpleOrderAlloc();
    @Resource
    private GetUserAddr getUserAddr = new GetUserAddr();

    @Override
    public JSONObject confirmOrder(String orderInfo, int addrID) {
        JSONObject result = new JSONObject();
        if (orderInfo == null || orderInfo.equals("")) {
            result.put("errorCode", 7);
            result.put("errorMsg", "No order information");
        } else {
            OrderEntity orderEntity = OrderEntity.fromJsonString(orderInfo, false);
            if (orderEntity.getU_id() == null || orderEntity.getU_id().equals("") ||
                    orderEntity.getDetail() == null || orderEntity.getDetail().equals("") ||
                    orderEntity.getTotal_price().equals(new BigDecimal(0.0)) ||
                    getUserAddr.getAddrByID(orderEntity.getU_id(), addrID) == null) {
                result.put("errorCode", 6);
                result.put("errorMsg", "Lack of necessary order information");
            } else {
                String d_addr = JSON.toJSONString(getUserAddr.getAddrByID(orderEntity.getU_id(), addrID));
                orderEntity.setO_delivery_addr(d_addr);
                //simple order allocation
                switch (simpleOrderAlloc.simpleAlloc(orderEntity)) {
                    case 0:
                        orderDAO.insert(orderEntity);
                        List<OrderEntity> results = orderDAO.query(orderEntity.getO_id());
                        if (results.size() == 1) {
                            result.put("errorCode", 0);
                            result.put("o_id", orderEntity.getO_id());
                        } else {
                            result.put("errorCode", 1);
                            result.put("errorMsg", "System Error : Insert Error!");
                            orderDAO.deleteRecord(orderEntity.getO_id());
                        }
                        break;
                    case 1:
                        result.put("errorCode", 2);
                        result.put("errorMsg", "Out of range of delivery service");
                        break;
                    case 2:
                        result.put("errorCode", 3);
                        result.put("errorMsg", "No factory available");
                        break;
                    case 3:
                        result.put("errorCode", 4);
                        result.put("errorMsg", "Lack of user address");
                        break;
//                    case 4:
//                        result.put("errorCode", 5);
//                        result.put("errorMsg", "No such user");
                }
            }
        }

        return result;
    }

    @Override
    public JSONObject payOrder(String o_id) {
        return null;
    }

    @Override
    public List<OrderEntity> getAllOrder(Integer pno, Integer pageSize) {
        int startIndex = (pno - 1) * pageSize;
        return orderDAO.queryAll(startIndex, pageSize);
    }

    @Override
    public OrderEntity modifyOrderStatus(Integer status, String orderId) {
        List<OrderEntity> query = orderDAO.query(orderId);
        if (ObjectUtils.isEmpty(query)) return null;
        orderDAO.updateDeliveryState(orderId, status);
        return orderDAO.query(orderId).get(0);
    }
}
