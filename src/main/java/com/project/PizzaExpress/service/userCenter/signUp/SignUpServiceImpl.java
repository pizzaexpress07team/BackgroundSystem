package com.project.PizzaExpress.service.userCenter.signUp;

import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SignUpServiceImpl implements ISignUpService{

    @Autowired
    private UserDAO userDAO;

    public boolean signUp(String userInfo) {
        UserEntity userEntity = UserEntity.fromJsonString(userInfo);
        userDAO.insert(userEntity);
        List<String> result = userDAO.query(userEntity.getUsername());
        return (result.size() == 0);
    }
}
