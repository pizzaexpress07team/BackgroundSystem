package com.project.PizzaExpress.service.deliverymanManage.updateInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDeliManImpl implements IUpdateDeliverymanInfoService {

    @Autowired
    private DeliverymanDAO deliverymanDAO;

    public JSONObject updateLocation(String info)
    {
        JSONObject result = new JSONObject();
        JSONObject location = JSON.parseObject(info);
        String d_id = location.getString("d_id");
        if (d_id == null || d_id.equals(""))
        {
            result.put("errorCode", 2);
            result.put("errorMsg", "Deliveryman ID is empty");
        }
        else if (location.containsKey("lng") && location.containsKey("lat"))
        {
            double lng = location.getDoubleValue("lng");
            double lat = location.getDoubleValue("lat");
            if (deliverymanDAO.updateLocation(d_id, lng, lat) == 1)
            {
                result.put("errorCode", 0);
                result.put("d_id", d_id);
            }
            else
            {
                result.put("errorCode", 1);
                result.put("errorMsg", "System Error : Update Error");
            }
        }
        else
        {
            result.put("errorCode", 3);
            result.put("errorMsg", "Lack of location information");
        }

        return result;
    }
}
