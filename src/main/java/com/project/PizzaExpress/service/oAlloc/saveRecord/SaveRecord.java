package com.project.PizzaExpress.service.oAlloc.saveRecord;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.OrderDAO;
import com.project.PizzaExpress.entity.OrderFactoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveRecord {

    @Autowired
    private OrderDAO orderDAO;

    public int saveRecord(String o_id, String f_list)
    {
        if (o_id == null || o_id.equals("") ||
            f_list == null || f_list.equals(""))
            return -1;
        else
        {
            orderDAO.insertRecord(new OrderFactoryEntity(o_id, f_list));
            if (orderDAO.queryRecord(o_id).size() == 1)
                return 0;
            else
                return 1;
        }
    }
}
