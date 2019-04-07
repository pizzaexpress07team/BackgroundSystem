package com.project.PizzaExpress.service.menuInfo;

import com.project.PizzaExpress.service.menuInfo.view.IViewMenuInfoService;
import com.project.PizzaExpress.service.menuInfo.view.ViewMenuInfoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewMenuInfoServiceImplTest {

    @Resource
    private IViewMenuInfoService viewMenuInfoService = new ViewMenuInfoServiceImpl();

    @Test
    public void displayMenu() {
        Assert.assertThat(viewMenuInfoService.displayMenu(), is("{\"total\":2,\"errorCode\":0,\"list\":[{\"f_id\":1,\"is_empty\":false,\"p_id\":1,\"p_name\":\"test1\",\"p_picture\":\"483134864866\",\"p_size\":\"50\",\"p_type\":\"111\",\"price\":10},{\"f_id\":2,\"is_empty\":false,\"p_id\":2,\"p_name\":\"test2\",\"p_picture\":\"846438844835\",\"p_size\":\"50\",\"p_type\":\"222\",\"price\":20}]}"));
    }
}