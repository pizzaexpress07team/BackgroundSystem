package com.project.PizzaExpress.service.userCenter.delete;

import com.alibaba.fastjson.JSONObject;

public interface IDeleteInfoService {
    JSONObject deleteUserInfoByUid(String uid);
}
