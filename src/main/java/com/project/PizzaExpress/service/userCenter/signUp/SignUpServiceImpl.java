package com.project.PizzaExpress.service.userCenter.signUp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignUpServiceImpl implements ISignUpService{

    @Autowired
    private UserDAO userDAO;

    public String signUp(String userInfo) {
        UserEntity userEntity = UserEntity.fromJsonString(userInfo);
        JSONObject result = new JSONObject();
        if (userDAO.query(userEntity.getUsername()).size() != 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Username exists");
        }
        else {
            userDAO.insert(userEntity);
            if (userDAO.query(userEntity.getUsername()).size() == 1)
            {
                result.put("errorCode", 0);
            }
            else
            {
                result.put("errorCode", 2);
                result.put("errorMsg", "System Error : Sign Up Error");
            }
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
