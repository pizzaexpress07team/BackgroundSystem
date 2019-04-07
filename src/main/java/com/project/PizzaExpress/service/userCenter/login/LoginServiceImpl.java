package com.project.PizzaExpress.service.userCenter.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoginServiceImpl implements  ILoginService{

    @Autowired
    private UserDAO userDAO;

    /**
     * @param username username
     * @param password password
     * @return errorCode:
     *         0 : 正常
     *         1 : 用户名不存在
     *         2 : 用户名存在但密码错误
     */
    public String login(String username, String password)
    {
        if (userDAO == null)
            System.out.println("error");
        List<UserEntity> passwords = userDAO.queryUserInfo(username);

        JSONObject result = new JSONObject();
        if (passwords == null || passwords.size() == 0)
        {
            result.put("errorCode", 1);
            result.put("errorMsg", "Error username");
            return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        }
        for (UserEntity _password : passwords)
        {
            if (password != null && password.equals(_password.getPassword()))
            {
                result.put("errorCode", 0);
                result.put("username", username);
                return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
            }
        }
        result.put("errorCode", 2);
        result.put("errorMsg", "Wrong password");
        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
    }
}
