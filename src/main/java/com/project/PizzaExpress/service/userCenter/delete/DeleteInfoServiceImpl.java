package com.project.PizzaExpress.service.userCenter.delete;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DeleteInfoServiceImpl implements IDeleteInfoService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public JSONObject deleteUserInfoByUid(String uid){
        JSONObject result = new JSONObject();
        List<UserEntity> query = userDAO.queryUserInfo(uid);
        if(ObjectUtils.isEmpty(query)){
            result.put("errorCode", 1);
            result.put("errorMsg", "No such user item");
        }else{
            //TODO
        }
        return result;

    }
}
