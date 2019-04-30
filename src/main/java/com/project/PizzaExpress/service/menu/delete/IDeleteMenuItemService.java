package com.project.PizzaExpress.service.menu.delete;

import com.alibaba.fastjson.JSONObject;

public interface IDeleteMenuItemService {

    JSONObject deleteMenuItemWithRes(String p_id);
}
