package springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.dto.UserDTO;
import springboot.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserDTO userDTO);

    User findByEmail(String email);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUserById(int id);

    Page<User> findPaginated(int pageNo, int pageSize);
}