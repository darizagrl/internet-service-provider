package springboot.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springboot.dto.UserDTO;
import springboot.entity.MyUserDetails;
import springboot.entity.Role;
import springboot.entity.User;
import springboot.repository.UserRepo;
import springboot.service.UserService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
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
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.DEFAULT_DIRECTION.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepo.findAll(pageable);
    }

    @Override
    public double getUserBalance(String email) {
        Optional<User> optional = userRepo.findByEmail(email);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            logger.error("User not found for email : " + email);
            throw new RuntimeException("User not found for email : " + email);
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
            logger.error("User not found for email : " + email);
            throw new RuntimeException("User not found for email : " + email);
        }
        user.setBalance(balance + user.getBalance());
        if (user.isBlocked() && user.getBalance() >= 0) {
            user.setBlocked(false);
        }
        userRepo.save(user);
    }
}
