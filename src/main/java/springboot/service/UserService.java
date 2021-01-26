package springboot.service;

import springboot.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(int id);

    void deleteUserById(int id);
}