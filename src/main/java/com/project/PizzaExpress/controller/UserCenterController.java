package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.userCenter.login.ILoginService;
import com.project.PizzaExpress.service.userCenter.modify.IModifyService;
import com.project.PizzaExpress.service.userCenter.modify.ModifyServiceImpl;
import com.project.PizzaExpress.service.userCenter.signUp.ISignUpService;
import com.project.PizzaExpress.service.userCenter.login.LoginServiceImpl;
import com.project.PizzaExpress.service.userCenter.signUp.SignUpServiceImpl;
import com.project.PizzaExpress.service.userCenter.viewInfo.IViewInfoService;
import com.project.PizzaExpress.service.userCenter.viewInfo.ViewInfoServiceImpl;
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
    private ISignUpService signUpService = new SignUpServiceImpl();
    private IModifyService modifyService = new ModifyServiceImpl();
    private IViewInfoService viewInfoService = new ViewInfoServiceImpl();

    /* errorCode:
     * 0 : 正常
     * 1 : 用户名不存在
     * 2 : 用户名存在但密码错误
     */
    @RequestMapping("/login")
    public void login(HttpSession session, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        session.setAttribute("return", loginService.login(username, password));
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping("/signUp")
    public void signUp(HttpSession session, @RequestBody String userInfo) {
        session.setAttribute("errorCode", signUpService.signUp(userInfo));
    }

    @RequestMapping("/modify")
    public void modify(HttpSession session, @RequestBody String userInfo) {
        session.setAttribute("errorCode", modifyService.modify(userInfo));
    }

    @RequestMapping("/view")
    public void viewInfo(HttpSession session, @RequestBody String username)
    {
        session.setAttribute("errorCode", viewInfoService.viewInfo(username));
    }
}
