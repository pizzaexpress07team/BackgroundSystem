package com.project.PizzaExpress.service.menu.view;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import com.project.PizzaExpress.entity.PizzaWithResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ViewMenuInfoServiceImpl implements  IViewMenuInfoService {

    @Autowired
    private PizzaDAO pizzaDAO;

    @Override
    public String displayMenu()
    {
        List<PizzaEntity> pizzaList = pizzaDAO.queryAll();
        JSONObject result = new JSONObject();
        if (pizzaList == null || pizzaList.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Menu has not been found or it is empty!");
        }
        else
        {
            result.put("errorCode", 0);
            List<PizzaEntity> not_empty = new LinkedList<>();
            for (PizzaEntity pe : pizzaList)
            {
                if (!pe.isIs_empty()) //不想前端传输is_empty为true的pizza数据
                {
                    not_empty.add(pe);//todo 不传输部分属性
                }
            }
            result.put("list", not_empty);
            result.put("total", not_empty.size());
        }

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    @Override
    public JSONObject totalDisplay()
    {
        List<PizzaWithResEntity> pizzaList = pizzaDAO.queryAllWithRes();
        JSONObject result = new JSONObject();
        if (pizzaList == null || pizzaList.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Menu has not been found or it is empty!");
        }
        else
        {
            result.put("errorCode", 0);
            List<PizzaWithResEntity> not_empty = new LinkedList<>();
            for (PizzaWithResEntity pe : pizzaList)
            {
                if (!pe.isIs_empty()) //不想前端传输is_empty为true的pizza数据
                {
                    System.out.println(pe.getResource());
                    pe.setF_id(null);//不传输f_id属性
                    not_empty.add(pe);
                }
            }
            result.put("list", not_empty);
            result.put("total", not_empty.size());
        }

        return result;
    }

    @Override
    public List<PizzaWithResEntity> getMenuStatus(String p_name){
        List<PizzaWithResEntity> query = pizzaDAO.queryPizzaInfoWithResLike("%" + p_name + "%");
        return query;
    }
}
