package com.project.PizzaExpress.service.userCenter.login;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceImplTest {

    @Resource
    private ILoginService loginService = new LoginServiceImpl();

    @Test
    public void login() {
        Assert.assertThat(loginService.login("test", "test"), is("{\"errorCode\":0,\"username\":\"test\"}"));
        Assert.assertThat(loginService.login("error username", "test"), is("{\"errorCode\":1,\"errorMsg\":\"Error username\"}"));
        Assert.assertThat(loginService.login("test", "error password"), is("{\"errorCode\":2,\"errorMsg\":\"Wrong password\"}"));
    }
}