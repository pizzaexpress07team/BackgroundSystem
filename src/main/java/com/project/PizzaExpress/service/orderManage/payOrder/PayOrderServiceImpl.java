package com.project.PizzaExpress.service.orderManage.payOrder;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.service.oAlloc.completeAlloc.CompleteAlloc;
import com.project.PizzaExpress.service.stockManage.resource.IResourceManageService;
import com.project.PizzaExpress.service.stockManage.resource.ResourceManageServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class PayOrderServiceImpl implements IPayOrderService{

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DeliverymanDAO deliverymanDAO;

    @Resource
    private CompleteAlloc completeAlloc = new CompleteAlloc();
    @Resource
    private IResourceManageService resourceManageService = new ResourceManageServiceImpl();

    public JSONObject payOrder(String o_id)
    {
        JSONObject result = new JSONObject();
        List<OrderEntity> orders = orderDAO.query(o_id);
        if (orders == null || orders.size() == 0)
        {
            result.put("errorCode", 3);
            result.put("errorMsg", "No such order");
        }
        else
        {
            OrderEntity order = orders.get(0);
            JSONObject path = completeAlloc.completeAlloc(order, 30 * 60);
            if (path.getIntValue("errorCode") == 0)
            {
                order.setDelivery_state(1);
                order.setF_id(path.getString("f_id"));
                JSONObject resource = resourceManageService.decreaseResource(order);
                if (resource.getIntValue("errorCode") != 0)
                {
                    result.put("errorCode", 4);
                    result.put("errorMsg", "Lack of resource");
                    return result;
                }
                order.setO_pay_state(1);
                order.setO_pay_time(new Timestamp(new Date().getTime()));
                order.setPay_id("not exists");
                int a = orderDAO.updateDeliveryState(o_id, 1);
                int b = orderDAO.updateFid(o_id, path.getString("f_id"));
                int c = orderDAO.updatePayState(o_id, 1);
                int d = orderDAO.updatePaytime(o_id, new Timestamp(new Date().getTime()));
                if (a == 1 && b == 1 && c == 1 && d == 1)
                {
                    List<DeliverymanEntity> dms = deliverymanDAO.queryAll();
                    int index = (int)(Math.random() * dms.size());
                    DeliverymanEntity dm = dms.get(index);
                    orderDAO.updateDid(o_id,dm.getD_id());
                    result.put("errorCode", 0);
                    result.put("o_id", o_id);
                    result.put("duration", path.getDoubleValue("duration"));
                    result.put("steps", path.getJSONArray("steps"));
                    result.put("deliveryman", dm);
                }
                else
                {
                    result.put("errorCode", 1);
                    result.put("errorMsg", "System Error : Update Error");
                }
            }
            else
            {
                result.put("errorCode", 2);
                result.put("errorMsg", "No Available Factory");
            }
        }

        return result;
    }
}
