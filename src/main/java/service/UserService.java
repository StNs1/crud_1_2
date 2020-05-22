package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(long id);
    boolean addUser(User user);
    boolean editUser(User user);
    boolean deleteUser(User user);
    void cleanUp();
}
