package com.project.PizzaExpress.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PizzaTypeEntity {

    private String p_name;
    private String resource;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Map<String, Integer> getResNumMap()
    {
        Map<String, Integer> map = new HashMap<>();
        JSONArray res_arr = JSON.parseArray(resource);
        for (int i = 0; i < res_arr.size(); i++)
        {
            JSONObject res = res_arr.getJSONObject(i);
            map.put(res.getString("r_id"), res.getIntValue("num"));
        }
        return map;
    }
}
