package com.project.PizzaExpress.service.deliverymanManage.quit;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class DeleteDeliverymanServiceImpl implements IDeleteDeliverymanService {
    @Autowired
    private DeliverymanDAO deliverymanDAO;

    public JSONObject deleteDeliveryman(String d_id){
        JSONObject result = new JSONObject();
        List<DeliverymanEntity> query = deliverymanDAO.query(d_id);
        if(ObjectUtils.isEmpty(query)){
            result.put("errorCode", 1);
            result.put("errorMsg", "No such deliveryman item");
        }else {
            if(query.get(0).getState() == 1){
                result.put("errorCode", 2);
                result.put("errorMsg", "this deliveryman have order");
            }else {
                deliverymanDAO.delete(d_id);
                result.put("errorCode", 0);
                result.put("successDelete", query);
            }
        }
        return result;
    }
}
