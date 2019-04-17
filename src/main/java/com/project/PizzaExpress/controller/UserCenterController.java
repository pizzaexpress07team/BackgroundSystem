package com.project.PizzaExpress.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.project.PizzaExpress.service.deliverymanManage.join.CreateNewDeliverymanServiceImpl;
import com.project.PizzaExpress.service.deliverymanManage.join.ICreateNewDeliverymanService;
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
    @Resource
    private ICreateNewDeliverymanService createNewDeliverymanService = new CreateNewDeliverymanServiceImpl();

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

    @RequestMapping("/modify")
    public String modify(HttpSession session, @RequestBody String userInfo) {
        String re = modifyService.modify(userInfo);
        session.setAttribute("return", re);
        return re;
    }

    @RequestMapping("/mod_addr")
    public String modifyAddr(HttpSession session,
                             @RequestParam(name = "username") String uid,
                             @RequestParam(name = "username") String addr) {
        String re = JSON.toJSONString(modifyService.modifyAddr(uid, addr), SerializerFeature.WriteMapNullValue);
        session.setAttribute("return", re);
        return re;
    }

    @RequestMapping("/view")
    public String viewInfo(HttpSession session, @RequestParam(name = "uid") String uid)
    {
        String userInfo = viewInfoService.viewInfo(uid);
        session.setAttribute("return", userInfo);
        return userInfo;
    }

    /* errorCode :
     * 0 : 正常
     * 1 : 数据库的插入过程出现异常
     * 2 : uid对应的用户信息中缺少姓名或电话信息
     * 3 : 没有找到f_id对应的factory
     * 4 : 数据库factory中存在重复f_id的项（正常情况下不会发生）
     * 5 : 没有找到uid对应的user
     * 6 : 数据库user中存在重复uid的项（正常情况下不会发生）
     */
    @RequestMapping("/dm/join")
    public String applyDeliveryman(@RequestParam(name = "uid") String uid, @RequestParam(name = "f_id") String f_id)
    {
        String re = JSON.toJSONString(createNewDeliverymanService.joinDeliveryman(uid, f_id), SerializerFeature.WriteMapNullValue);
        return re;
    }


}
