package com.project.PizzaExpress.service.userCenter.modify;

import com.alibaba.fastjson.JSONObject;

public interface IModifyService {

    String modify(String userInfo);

    JSONObject modifyAddr(String uid, String addr);
}
