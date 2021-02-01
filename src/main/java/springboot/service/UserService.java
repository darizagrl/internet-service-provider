package springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.dto.UserDTO;
import springboot.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(UserDTO userDTO);

    Optional<User> findByEmail(String email);

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUserById(int id);

    Page<User> findPaginated(int pageNo, int pageSize);
}