package com.project.PizzaExpress.controller;

import com.project.PizzaExpress.service.userCenter.login.ILoginService;
import com.project.PizzaExpress.service.userCenter.modify.IModifyService;
import com.project.PizzaExpress.service.userCenter.modify.ModifyServiceImpl;
import com.project.PizzaExpress.service.userCenter.signUp.ISignUpService;
import com.project.PizzaExpress.service.userCenter.login.LoginServiceImpl;
import com.project.PizzaExpress.service.userCenter.signUp.SignUpServiceImpl;
import com.project.PizzaExpress.service.userCenter.viewInfo.IViewInfoService;
import com.project.PizzaExpress.service.userCenter.viewInfo.ViewInfoServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserCenterController {

    @Resource
    private ILoginService loginService = new LoginServiceImpl();
    @Resource
    private ISignUpService signUpService = new SignUpServiceImpl();
    @Resource
    private IModifyService modifyService = new ModifyServiceImpl();
    @Resource
    private IViewInfoService viewInfoService = new ViewInfoServiceImpl();

    /* errorCode:
     * 0 : 正常
     * 1 : 用户名不存在
     * 2 : 用户名存在但密码错误
     */
    @RequestMapping("/login")
    public String login(HttpSession session, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        String result = loginService.login(username, password);
        session.setAttribute("return", result);
        return result;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @RequestMapping("/signUp")
    public String signUp(HttpSession session, @RequestBody String userInfo) {
        String re = signUpService.signUp(userInfo);
        session.setAttribute("return", re);
        return re;
    }

    /* return value:
     * 0 : 成功
     * 1 : 失败
     */
    @RequestMapping("/modify")
    public String modify(HttpSession session, @RequestBody String userInfo) {
        String re = modifyService.modify(userInfo);
        session.setAttribute("return", re);
        return re;
    }

    @RequestMapping("/view")
    public String viewInfo(HttpSession session, @RequestParam(name = "username") String username)
    {
        String userInfo = viewInfoService.viewInfo(username);
        session.setAttribute("return", userInfo);
        return userInfo;
    }
}
