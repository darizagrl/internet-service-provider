package springboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.dto.UserDTO;
import springboot.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserDTO userDTO);

    void create(User user);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUserById(int id);
}