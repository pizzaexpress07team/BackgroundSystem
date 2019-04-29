package com.project.PizzaExpress.service.deliverymanManage.view;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.entity.DeliManWithOrderEntity;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ViewDeliverymanInfoServiceImpl implements IViewDeliverymanInfoService {

    @Autowired
    DeliverymanDAO deliverymanDAO;

    @Override
    public JSONObject viewInfo(String d_id) {
        JSONObject result = new JSONObject();
        List<DeliverymanEntity> dmList = deliverymanDAO.query(d_id);
        if (dmList == null || dmList.size() < 1)
        {
            result.put("errorCode", 2);
            result.put("errorMsg", "No such deliveryman");
        }
        else if (dmList.size() == 1)
        {
            DeliverymanEntity deliverymanEntity = dmList.get(0);
            JSONObject info = deliverymanEntity.toJsonObject();
            result.put("errorCode", 0);
            result.put("dmInfo", info);
        }
        else
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "System Error : Duplicate Deliveryman ID");
        }

        return result;
    }

    @Override
    public JSONObject viewAllInfo() {
        List<DeliverymanEntity> dmList = deliverymanDAO.queryAll();

        JSONObject result = new JSONObject();
        if (dmList == null || dmList.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "There is no deliveryman at all!");
        }
        else
        {
            result.put("errorCode", 0);
            result.put("list", dmList);
            result.put("total", dmList.size());
        }

        return result;
    }

    @Override
    public JSONObject viewByFactory(String f_id) {
        List<DeliverymanEntity> dmList = deliverymanDAO.queryDeliverymansByFid(f_id);

        JSONObject result = new JSONObject();
        if (dmList == null || dmList.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "There is no deliveryman in this factory!");
        }
        else
        {
            result.put("errorCode", 0);
            result.put("list", dmList);
            result.put("total", dmList.size());
        }

        return result;
    }

    @Override
    public DeliverymanEntity getDeliverStatus(String deliverId) {
        List<DeliverymanEntity> query = deliverymanDAO.query(deliverId);
        if(ObjectUtils.isEmpty(query))return null;
        return query.get(0);
    }

    @Override
    public List<DeliverymanEntity> getDeliverStatusLike(String d_name){
        List<DeliverymanEntity> query = deliverymanDAO.queryLike("%" + d_name + "%");
        return query;
    }

    @Override
    public List<DeliverymanEntity> getAllDeliveryManByPage(Integer pno, Integer pageSize){
        int startIndex = (pno - 1) * pageSize;
        return deliverymanDAO.queryAllByPage(startIndex, pageSize);
    }

    @Override
    public JSONObject getAllDeliveryOrderByPage(Integer pno, Integer pageSize){
        int startIndex = (pno - 1) * pageSize;
        List<DeliManWithOrderEntity> list = deliverymanDAO.queryDelimanOrderByPage(startIndex, pageSize);
        String total = deliverymanDAO.queryDelimanOrderSize();
        JSONObject result = new JSONObject();
        result.put("list",list);
        result.put("total",total);
        return result;
    }
}
