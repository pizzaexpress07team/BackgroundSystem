package com.project.PizzaExpress.service.menu.insert;

import com.alibaba.fastjson.JSONObject;

public interface IInsertMenuItemService {

    String insertMenuItem(String menuInfo);
    JSONObject insertMenuItemWithRes(String menuInfoWithRes);
}
