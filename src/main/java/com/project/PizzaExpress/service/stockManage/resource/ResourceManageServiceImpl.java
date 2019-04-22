package com.project.PizzaExpress.service.stockManage.resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.FactoryResourceDAO;
import com.project.PizzaExpress.dao.PizzaTypeDAO;
import com.project.PizzaExpress.entity.FactoryResourceEntity;
import com.project.PizzaExpress.entity.OrderEntity;
import com.project.PizzaExpress.entity.PizzaTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceManageServiceImpl implements IResourceManageService{

    @Autowired
    private PizzaTypeDAO pizzaTypeDAO;
    @Autowired
    private FactoryResourceDAO factoryResourceDAO;

    @Override
    public JSONObject decreaseResource(OrderEntity order) {
        JSONObject result = new JSONObject();
        String f_id = order.getF_id();
        Map<String, FactoryResourceEntity> fac_res = getFacResMap(factoryResourceDAO.queryByFactory(f_id));
        JSONArray pizzaList = JSON.parseArray(order.getDetail());
        for (int i = 0; i < pizzaList.size(); i++)
        {
            JSONObject pizza = pizzaList.getJSONObject(i);
            int pizza_count = pizza.getIntValue("num");
            String p_name = pizza.getString("name");
            List<PizzaTypeEntity> res_list = pizzaTypeDAO.query(p_name);
            Map<String, Integer> resources = res_list.get(0).getResNumMap();
            for (String key : resources.keySet())
            {
                int res_change = - (resources.get(key) * pizza_count);
                if (!fac_res.get(key).changeResNum(res_change))
                {
                    result.put("errorCode", 1);
                    result.put("errorMsg", "Lack of resources");
                    return result;
                }
            }
        }
        for (String key : fac_res.keySet())
        {
            if (factoryResourceDAO.updateResourceNum(f_id, key, fac_res.get(key).getR_num()) != 1)
            {
                result.put("errorCode", 2);
                result.put("errorMsg", "System Error : Update Error");
                return result;
            }
        }
        result.put("errorCode", 0);
        result.put("o_id", order.getO_id());
        return result;
    }

    Map<String, FactoryResourceEntity> getFacResMap(List<FactoryResourceEntity> fac_res)
    {
        Map<String, FactoryResourceEntity> map = new HashMap<>();
        for (int i = 0; i < fac_res.size(); i++)
        {
            FactoryResourceEntity entity = fac_res.get(0);
            map.put(entity.getR_id(), entity);
        }
        return map;
    }
}
