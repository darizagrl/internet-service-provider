package springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.controller.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private HomeController homeController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private LoginController loginController;
    @Autowired
    RegFormController regFormController;
    @Autowired
    TariffController tariffController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(homeController).isNotNull();
        assertThat(accountController).isNotNull();
        assertThat(loginController).isNotNull();
        assertThat(regFormController).isNotNull();
        assertThat(tariffController).isNotNull();
    }

    @Test
    public void contextLoadsIndex() throws Exception {
        assertThat(homeController).isNotNull();
    }

    @Test
    public void contextLoadsAccount() throws Exception {
        assertThat(accountController).isNotNull();
    }

    @Test
    public void contextLoadsLogin() throws Exception {
        assertThat(loginController).isNotNull();
    }

    @Test
    public void contextLoadsRegistration() throws Exception {
        assertThat(regFormController).isNotNull();
    }

    @Test
    public void contextLoadsTariffs() throws Exception {
        assertThat(tariffController).isNotNull();
    }
}
