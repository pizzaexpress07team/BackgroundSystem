package com.project.PizzaExpress.service.oAlloc.completeAlloc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.entity.OrderFactoryEntity;
import com.project.PizzaExpress.service.oAlloc.DeliverymanHeap;
import com.project.PizzaExpress.service.oAlloc.PositionProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

@Service
public class CompleteAlloc {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private DeliverymanDAO deliverymanDAO;

    private static final String AK = "RCghRX2vPhBZibGwsjegFztulsnlMAsQ";
    private static final int IS_IDLE = 0;

    public int completeAlloc(OrderEntity order, int last_time)
    {
        if (order == null || order.getO_id() == null || order.getO_id().equals(""))
            return 1;
        Vector<Double> u_pos = PositionProcess.processAddr(order.getO_delivery_addr());
        if (u_pos == null || u_pos.size() < 2)
            return 2;
        List<OrderFactoryEntity> pre_fac = orderDAO.queryRecord(order.getO_id());
        if (pre_fac == null || pre_fac.size() == 0)
            return 3;
        JSONArray f_arr = JSON.parseArray(pre_fac.get(0).getF_list());
        for (int i = 0; i < f_arr.size(); i++)
        {
            JSONObject fac = f_arr.getJSONObject(i);
            List<DeliverymanEntity> dms = deliverymanDAO.queryDeliverymans(fac.getString("f_id"));
            if (dms == null || dms.size() == 0)
                return 4;

            Vector<Object> path1 = new Vector<>();
            try {
                JSONObject f_to_u = getPath(fac.getDoubleValue("lng"), fac.getDoubleValue("lat"), u_pos.get(0), u_pos.get(1));
                JSONArray routes = f_to_u.getJSONArray("routes");
                path1 = getShortestRoute(routes, last_time);
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            DeliverymanHeap heap = new DeliverymanHeap();
            for (DeliverymanEntity dm : dms)
            {
                if (dm.getState() == IS_IDLE)
                {
                    try {
                        JSONObject d_to_f = getPath(dm.getLng().doubleValue(), dm.getLat().doubleValue(),
                                                    fac.getDoubleValue("lng"), fac.getDoubleValue("lat"));
                        JSONArray routes = d_to_f.getJSONArray("routes");
                        double min_time = last_time - (Double)path1.get(0);
                        Vector<Object> path2 = getShortestRoute(routes, min_time);
                        if (path2.size() == 2)
                        {
                            Vector<Object> avai_dm = new Vector<>();
                            avai_dm.add(dm.getD_id());
                            avai_dm.add((Double)path2.get(0) + (Double)path1.get(0));
                            avai_dm.add(path2.get(1));
                            avai_dm.add(path1.get(1));
                            heap.insert(avai_dm);
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            //todo
//            if (heap.size() == 0)
//                return
        }
        return 0;
    }

    public JSONObject getPath(double o_lng, double o_lat, double d_lng, double d_lat) throws IOException {
        String url = "http://api.map.baidu.com/directionlite/v1/riding?origin=" + o_lng + "," + o_lat + "&destination=" + d_lng + "," + d_lat + "&ak="+ AK;
        URL myURL = null;

        URLConnection httpsConn;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader in = null;
        BufferedReader br = null;
        try {
            httpsConn = myURL.openConnection();// 不使用代理   
            if (httpsConn != null) {
                in = new InputStreamReader(httpsConn.getInputStream(), StandardCharsets.UTF_8);
                br = new BufferedReader(in);
                String data = null;
                while((data= br.readLine())!=null){
                    JSONObject result = JSONObject.parseObject(data);
                    String status = result.getString("status");
                    if (status != null && status.equals("0"))
                        return result.getJSONObject("result");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                in.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return null;
    }

    private Vector<Object> getShortestRoute(JSONArray routes, double last_time)
    {
        Vector<Object> path = new Vector<>();
        double min_time = last_time;
        for (int r = 0; r < routes.size(); r++)
        {
            JSONObject route = routes.getJSONObject(r);
            if (route.getDoubleValue("duration") < min_time)
            {
                min_time = route.getDoubleValue("duration");
                path.add(min_time);
                path.add(route.getString("steps"));
            }
        }
        return path;
    }
}
