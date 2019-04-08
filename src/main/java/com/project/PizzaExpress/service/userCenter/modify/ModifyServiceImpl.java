package com.project.PizzaExpress.service.userCenter.modify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyServiceImpl implements IModifyService{

    @Autowired
    private UserDAO userDAO;

    public String modify(String userInfo)
    {
        JSONObject result = new JSONObject();
        if (userInfo == null || userInfo.equals(""))
        {
            result.put("errorCode", 2);
            result.put("errorMsg", "No user information");
        }
        else {
            UserEntity userEntity = UserEntity.fromJsonString(userInfo, true);
            if (userEntity == null || userEntity.getUid().equals(""))
            {
                result.put("errorCode", 3);
                result.put("errorMsg", "Lack of necessary user information");
            }
            else if (userDAO.update(userEntity) == 1)
            {
                result.put("errorCode", 0);
                result.put("uid", userEntity.getUid());
            }
            else
            {
                result.put("errorCode", 1);
                result.put("errorMsg", "System Error : Update Duplicated");
            }
        }

        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
