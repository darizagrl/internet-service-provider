package springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import springboot.dto.UserDTO;
import springboot.entity.Tariff;
import springboot.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User save(UserDTO userDTO);

    Optional<User> findByEmail(String email);

    void deleteUserById(int id);

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortOrder);

    double getUserBalance(String email);

    void updateUserBalance(String email, double balance);
}