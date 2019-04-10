package com.project.PizzaExpress.service.oAlloc.simpleAlloc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.FactoryDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.FactoryEntity;
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

    public int simpleAlloc(String o_id, String uid, String addrID)
    {
//        List<OrderEntity> order = orderDAO.query(o_id);
//        if (order == null || order.size() == 0)
//            return 5;

        List<UserEntity> user = userDAO.queryUserInfo(uid);
        if (user == null || user.size() == 0)
            return 4;

        Vector<Float> u_pos = PositionProcess.getUserPosition(user.get(0).getUid(), addrID);
        if (u_pos == null)
            return 3;

        List<FactoryEntity> factories = factoryDAO.queryAll();
        if (factories == null || factories.size() == 0)
            return 2;

        List<Vector<Object>> f_pos = new LinkedList<>();
        for (FactoryEntity fe : factories)
            f_pos.add(PositionProcess.getFactoryPosition(fe.getF_id(), fe.getF_addr()));

        JSONArray f_array = new JSONArray();
        for (Vector<Object> pos : f_pos)
        {
            if (pos != null && isInRange(PositionProcess.getDistance(
                            (Float)pos.get(1),
                            (Float)pos.get(2),
                            u_pos.get(0),
                            u_pos.get(1)), 5000))
            {
                JSONObject temp = new JSONObject();
                temp.put("f_id", pos.get(0).toString());
                f_array.add(temp);
            }
        }
        if (f_array.size() == 0)
            return 1;
        else
        {
            saveRecord.saveRecord(o_id, f_array.toString());
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
