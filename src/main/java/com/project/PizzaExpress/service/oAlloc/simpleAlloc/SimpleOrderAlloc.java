package com.project.PizzaExpress.service.oAlloc.simpleAlloc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.FactoryDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.FactoryEntity;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.entity.OrderFactoryEntity;
import com.project.PizzaExpress.entity.UserEntity;
import com.project.PizzaExpress.service.oAlloc.PositionProcess;
import com.project.PizzaExpress.service.oAlloc.saveRecord.SaveRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

@Service
public class SimpleOrderAlloc {

    @Autowired
    private static UserDAO userDAO;
    @Autowired
    private static OrderDAO orderDAO;
    @Autowired
    private static FactoryDAO factoryDAO;
    @Resource
    private SaveRecord saveRecord = new SaveRecord();

    private static final double VELOCITY = 750;// meter per minute
    private static final double TIME_LIMIT_IN_MINUTES = 30;
    private static final double NO_DM_DISTANCE = 5000;

    private JSONArray preAlloc(Vector<Double> u_pos)
    {
        List<FactoryEntity> factories = factoryDAO.queryAll();
        if (factories == null || factories.size() == 0)
            return null;

        List<Vector<Object>> f_pos = new LinkedList<>();
        for (FactoryEntity fe : factories)
            f_pos.add(PositionProcess.getFactoryPosition(fe.getF_id(), fe.getF_addr()));

        JSONArray f_array = new JSONArray();
        for (Vector<Object> pos : f_pos)
        {
            if (pos != null && PositionProcess.getDistance(
                    (Double)pos.get(1),
                    (Double)pos.get(2),
                    u_pos.get(0),
                    u_pos.get(1)) < NO_DM_DISTANCE)
            {
                JSONObject temp = new JSONObject();
                temp.put("f_id", pos.get(0).toString());
                temp.put("lng", pos.get(1));
                temp.put("lat", pos.get(2));
                f_array.add(temp);
            }
        }
        return f_array;
    }

    public int simpleAlloc(OrderEntity order)
    {
        Vector<Double> u_pos = PositionProcess.processAddr(order.getO_delivery_addr());
        if (u_pos == null || u_pos.size() < 2)
            return 3;

        JSONArray f_list = preAlloc(u_pos);
        if (f_list == null)
            return 2;
        else if (f_list.size() == 0)
            return 1;

        JSONArray f_array = new JSONArray();
        for (int i = 0; i < f_list.size(); i++)
        {
            JSONObject pos = f_list.getJSONObject(i);
            if (pos != null && isInRange(PositionProcess.getDistance(
                            (Double) pos.get("lng"),
                            (Double) pos.get("lat"),
                            u_pos.get(0),
                            u_pos.get(1)), NO_DM_DISTANCE))
            {
                f_array.add(pos);
            }
        }
        if (f_array.size() == 0)
            return 1;
        else
        {
            saveRecord.saveRecord(order.getO_id(), f_array.toString());
            return 0;
        }
    }

    private boolean isInRange(double distance, double zero_distance)
    {
        double avg_dis = (4 * distance) / zero_distance - (4 * Math.pow(distance, 2)) / (5 * zero_distance);
        double bias = 0;
        return (avg_dis + bias) < VELOCITY * TIME_LIMIT_IN_MINUTES;
    }
}
