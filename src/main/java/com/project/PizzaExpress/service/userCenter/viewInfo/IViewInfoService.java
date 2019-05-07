package com.project.PizzaExpress.service.userCenter.viewInfo;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface IViewInfoService {

    String viewInfo(String uid);

    JSONObject getAllUser(Integer pno, Integer pageSize);

    JSONObject getByUserName(String username);
}
