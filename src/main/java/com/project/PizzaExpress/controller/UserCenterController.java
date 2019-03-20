package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.entity.UserEntity;
import com.project.PizzaExpress.service.login.ILoginService;
import com.project.PizzaExpress.service.login.ISignupService;
import com.project.PizzaExpress.service.login.LoginServiceImpl;
import com.project.PizzaExpress.service.login.SignupServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserCenterController {

    @Resource
    private ILoginService loginService = new LoginServiceImpl();
    private ISignupService signupService = new SignupServiceImpl();

    @RequestMapping("/login")
    public int login(HttpSession session, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        int login = loginService.login(username, password);
        if (login == 0) {
            session.setAttribute("User", 0);
        }
        return login;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        return;
    }

    @RequestMapping("/signUp")
    public void signUp(@RequestBody UserEntity userEntity) {
        signupService.signup(userEntity);
        return;
    }

    @RequestMapping("/modify")
    public void modify() {

    }
}
