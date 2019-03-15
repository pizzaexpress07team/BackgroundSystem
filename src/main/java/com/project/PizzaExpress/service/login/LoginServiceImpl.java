package com.project.PizzaExpress.service.login;

import com.project.PizzaExpress.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements  ILoginService{

    @Autowired
    private UserDAO userDAO;

    /**
     *
     * @param username
     * @param password
     * @return 0 : 正常
     *         1 : 用户名不存在
     *         2 : 用户名存在但密码错误
     */
    public int login(String username, String password)
    {
        List<String> passwords = userDAO.query(username);
        if (passwords == null || passwords.size() == 0)
            return 1;
        for (int i = 0; i < passwords.size(); i++)
        {
            if (password != null && password.equals(passwords.get(i)))
            {
                return 0;
            }
        }
        return 2;
    }
}
