package com.project.PizzaExpress.service.orderManage.withUser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserAddr {

    @Autowired
    private UserDAO userDAO;

    public JSONObject getAddrByID(String uid, int addrID)
    {
        List<UserEntity> u_list = userDAO.queryUserInfo(uid);
        if (u_list == null || u_list.size() == 0)
            return null;
        UserEntity user = u_list.get(0);
        if (user.getAddr() == null || user.getAddr().equals(""))
            return null;
        JSONArray addr = JSON.parseArray(user.getAddr());
        if (addr == null || addr.size() == 0)
            return null;
        return addr.getJSONObject(addrID);
    }
}
