package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entity.User;
import springboot.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepo.save(user);
    }

    @Override
    public User getUserById(int id) {
        Optional<User> optional = userRepo.findById(id);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found for id :: " + id);
        }
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepo.deleteById(id);
    }

}
