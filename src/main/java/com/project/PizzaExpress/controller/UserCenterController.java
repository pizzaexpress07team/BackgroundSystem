package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.login.ILoginService;
import com.project.PizzaExpress.service.login.LoginServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserCenterController {

    @Resource
    private ILoginService loginService = new LoginServiceImpl();

    @RequestMapping("/login")
    public int login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password)
    {
        return loginService.login(username, password);
    }

    @RequestMapping("/logout")
    public void logout()
    {

    }

    @RequestMapping("/signUp")
    public void signUp()
    {

    }

    @RequestMapping("/modify")
    public void modify()
    {

    }
}
