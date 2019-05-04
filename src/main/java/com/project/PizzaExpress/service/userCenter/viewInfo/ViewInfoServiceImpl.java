package com.project.PizzaExpress.service.userCenter.viewInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ViewInfoServiceImpl implements IViewInfoService {

    @Autowired
    private UserDAO userDAO;

    public String viewInfo(String uid)
    {
        List<UserEntity> results = userDAO.queryUserInfo(uid);
        JSONObject result = new JSONObject();
        if (results == null || results.size() < 1)
        {
            result.put("errorCode", 2);
            result.put("errorMsg", "No such user");
        }
        else if (results.size() == 1) {
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
        else
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "System Error : Duplicate User");
        }
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }

    @Override
    public JSONObject getAllUser(Integer pno, Integer pageSize) {
        JSONObject result = new JSONObject();
        int startIndex = (pno - 1) * pageSize;
        List<UserEntity> list = userDAO.queryAllUserInfo(startIndex, pageSize);
        String total = userDAO.queryUserSize();
        if(list.size() == 0){
            result.put("errorCode",1);
            result.put("errorMsg","No item in this page");
        }else{
            result.put("errorCode",0);
            result.put("list",list);
            result.put("dataBaseTotal",total);
        }
        return result;
    }
}
