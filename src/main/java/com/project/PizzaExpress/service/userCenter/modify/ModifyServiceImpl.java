package com.project.PizzaExpress.service.userCenter.modify;

import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyServiceImpl implements IModifyService{

    @Autowired
    private UserDAO userDAO;

    public boolean modify(String userInfo)
    {
        UserEntity userEntity = UserEntity.fromJsonString(userInfo, true);
        return (userDAO.update( userEntity.getUid(),
                                userEntity.getPassword(),
                                userEntity.getAddr(),
                                userEntity.getIs_admin(),
                                userEntity.getPhone(),
                                userEntity.getSina(),
                                userEntity.getQq()) == 1);
    }
}
