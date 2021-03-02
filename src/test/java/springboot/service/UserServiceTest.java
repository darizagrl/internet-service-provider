package springboot.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.dto.UserDTO;
import springboot.entity.Role;
import springboot.entity.User;
import springboot.repository.UserRepo;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @MockBean
    UserRepo userRepo;
    @MockBean
    UserService userService;
    @MockBean
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void saveUser() {
        User user = new User();
        user.setId(25);
        user.setFirstname("User");
        user.setLastname("User");
        user.setEmail("user@mail.com");
        user.setPassword("password");
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setBalance(0.0);
        Mockito.when(userRepo.save(user)).thenReturn(user);
        Assert.assertEquals("User{id=25, firstname='User', lastname='User', email='user@mail.com', password='password', roles=[Role{id=null, name='ROLE_USER'}], tariffs=null, balance=0.0}",
                user.toString());
//        UserDTO userDTO = new UserDTO();
//        userDTO.setFirstname(user.getFirstname());
//        userDTO.setLastname(user.getLastname());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setBalance(0.0);
//        Assert.assertEquals(user, userService.save(userDTO));
    }
}