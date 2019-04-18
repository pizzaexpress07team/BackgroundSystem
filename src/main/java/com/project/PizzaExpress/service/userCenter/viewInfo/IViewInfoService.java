package com.project.PizzaExpress.service.userCenter.viewInfo;

import com.project.PizzaExpress.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

public interface IViewInfoService {

    String viewInfo(String uid);

    List<UserEntity> getAllUser(Integer pno, Integer pageSize);
}
