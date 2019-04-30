package com.project.PizzaExpress.service.menu.view;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.PizzaWithResEntity;

import java.util.List;

public interface IViewMenuInfoService {
    String displayMenu();
    JSONObject totalDisplay();
    JSONObject getItemWithResByNameLike(String p_name);
    JSONObject getItemWithResByIdLike(String p_id);
    JSONObject getItemWithResById(String p_id);
}
