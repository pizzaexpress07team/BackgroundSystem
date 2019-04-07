package com.project.PizzaExpress.service.userCenter.viewInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewInfoServiceImpl implements IViewInfoService {

    @Autowired
    private UserDAO userDAO;

    public String viewInfo(String username)
    {
        List<UserEntity> results = userDAO.queryUserInfo(username);
        JSONObject result = new JSONObject();
        if (results.size() == 1) {
            UserEntity userEntity = results.get(0);
            JSONObject userInfo = userEntity.toJsonObject();
            result.put("errorCode", 0);
            result.put("userInfo", userInfo);
//            result.put("uid", userEntity.getUid());
//            result.put("username", userEntity.getUsername());
//            result.put("addr", userEntity.getAddr());
//            result.put("is_admin", userEntity.getIs_admin());
//            result.put("phone", userEntity.getPhone());
//            result.put("sina", userEntity.getSina());
//            result.put("qq", userEntity.getQq());
//            result.put("create_time", userEntity.getCreate_time());
        }
        else if (results.size() > 1)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "System Error : Duplicate Username");
        }
        else
        {
            result.put("errorCode", 2);
            result.put("errorMsg", "No such user");
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
