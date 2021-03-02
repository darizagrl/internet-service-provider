package springboot.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
    @Autowired
    LoginController loginController;

    @Test
    public void testLogin() {
        Assert.assertEquals("login", loginController.login());
    }

    public void testUserIndex() {
        Assert.assertEquals("redirect:/main", loginController.userIndex());
    }
}