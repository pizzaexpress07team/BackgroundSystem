package com.project.PizzaExpress.service.userCenter.viewInfo;

import org.apache.catalina.User;

import java.util.List;

public interface IViewInfoService {

    String viewInfo(String uid);

    List<User> getAllUser(Integer pno,Integer pageSize);
}
