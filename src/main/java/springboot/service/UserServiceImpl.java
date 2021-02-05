package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.dto.UserDTO;
import springboot.entity.MyUserDetails;
import springboot.entity.Role;
import springboot.entity.Tariff;
import springboot.entity.User;
import springboot.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        this.userRepo.save(user);
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepo.deleteById(id);
    }

    @Override
    public User save(UserDTO registration) {
        User user = new User();
        user.setFirstname(registration.getFirstname());
        user.setLastname(registration.getLastname());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setBalance(0.0);
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(MyUserDetails::new).get();
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepo.findAll(pageable);
    }

    @Override
    public double getUserBalance(String email) {
        Optional<User> optional = userRepo.findByEmail(email);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for email :: " + email);
        }
        return user.getBalance();
    }

    @Transactional
    @Override
    public void updateUserBalance(String email, double balance) {
        Optional<User> optional = userRepo.findByEmail(email);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for email :: " + email);
        }
        userRepo.updateBalance(email, balance + user.getBalance());
    }

    @Override
    public Set<Tariff> getUserTariffs(String email) {
        Optional<User> optional = userRepo.findByEmail(email);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for email :: " + email);
        }
        return user.getTariffs();
    }
}
