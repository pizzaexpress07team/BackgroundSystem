package com.project.PizzaExpress.service.menu.modify;

import com.alibaba.fastjson.JSONObject;

public interface IModifyMenuItemService {
    JSONObject modifyMenuItemWithRes(String pizzaInfoWithRes);
}
