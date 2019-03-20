package com.project.PizzaExpress.service.login;

import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class SignupServiceImpl implements ISignupService{
    @Autowired
    private UserDAO userDAO;

    public boolean signup(UserEntity userEntity) {
        userDAO.insert(userEntity);
        return true;
    }
}
