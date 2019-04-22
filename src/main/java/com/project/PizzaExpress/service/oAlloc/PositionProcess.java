package com.project.PizzaExpress.service.oAlloc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.FactoryDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.FactoryEntity;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Vector;

@Service
public class PositionProcess {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private static OrderDAO orderDAO;
    @Autowired
    private FactoryDAO factoryDAO;

    private static final double EARTH_RADIUS = 6371393;//m

    /**
     * @param arg (factory id) or (factory id, factory address)
     * @return (factory id, longitude, latitude)
     */
    public Vector<Object> getFactoryPosition(String ... arg)
    {
        Vector<Object> re = new Vector<>();
        Vector<Double> position;
        if (arg.length == 0)
            return null;
        else if (arg.length == 1)
        {
            List<FactoryEntity> factory = factoryDAO.query(arg[0]);
            if (factory == null || factory.size() == 0)
                return null;
            position = processAddr(factory.get(0).getF_addr());
        }
        else
            position = processAddr(arg[1]);
        if (position == null)
            return null;
        re.add(0, arg[0]);
        re.add(1, position.get(0));
        re.add(2, position.get(1));
        return re;

    }

    /**
     * @param uid user id
     * @return (longitude, latitude)
     */
    public Vector<Double> getUserPosition(String uid, int addrID)
    {
        Vector<Double> position = new Vector<>();
        List<UserEntity> user = userDAO.queryUserInfo(uid);
        if (user == null || user.size() == 0)
            return null;
        JSONObject location = JSON.parseArray(user.get(0).getAddr()).getJSONObject(addrID);
        position.add(0, location.getDoubleValue("lng"));
        position.add(1, location.getDoubleValue("lat"));

        return position;
    }

    public Vector<Double> processAddr(String addr)
    {
        Vector<Double> position = new Vector<>();
        if (addr == null || addr.equals(""))
            return null;
        JSONObject location = JSON.parseObject(addr);
        position.add(0, location.getDoubleValue("lng"));
        position.add(1, location.getDoubleValue("lat"));
        return position;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     * @param u_lng user position longitude
     * @param u_lat user position latitude
     * @param f_lng factory position longitude
     * @param f_lat factory position latitude
     * @return 距离
     */
    public static double getDistance(double u_lng, double u_lat, double f_lng, double f_lat)
    {
        double ruX = Math.toRadians(u_lng);
        double ruY = Math.toRadians(u_lat);
        double rfX = Math.toRadians(f_lng);
        double rfY = Math.toRadians(f_lat);
        double cos = Math.cos(ruY) * Math.cos(rfY) * Math.cos(ruX - rfX) + Math.sin(ruY) * Math.sin(rfY);
        return EARTH_RADIUS * Math.acos(cos);
    }
}
