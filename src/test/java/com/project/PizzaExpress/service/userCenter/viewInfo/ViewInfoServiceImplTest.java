package com.project.PizzaExpress.service.userCenter.viewInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewInfoServiceImplTest {

    @Resource
    private IViewInfoService viewInfoService = new ViewInfoServiceImpl();

    @Test
    public void viewInfo() {
        Assert.assertThat(viewInfoService.viewInfo("test"), is("{\"is_admin\":0,\"qq\":\"10000\",\"uid\":\"1\",\"create_time\":1546272000000,\"phone\":\"4008208820\",\"errorCode\":0,\"addr\":\"test\",\"sina\":\"test\",\"username\":\"test\"}"));
    }
}