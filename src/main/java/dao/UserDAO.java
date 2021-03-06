package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    boolean addUser(User user) throws SQLException;
    void editUser(User user) throws SQLException;
    void deleteUser(User user) throws SQLException;
    User getUserById(long id) throws SQLException;
    void dropTable() throws SQLException;
}
