package com.project.PizzaExpress.service.menu.view;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.entity.PizzaWithResEntity;

import java.util.List;

public interface IViewMenuInfoService {

    String displayMenu();
    JSONObject totalDisplay();
    List<PizzaWithResEntity> getMenuStatus(String p_name);
}
