package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static Connection getMySqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").         //login
                    append("password=root").      //password
                    append("&serverTimezone=UTC");   //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
    private static UserDAO getUserDAO() {
        return new UserDAO(getMySqlConnection());
    }

    public List<User> getAllUsers() {
        UserDAO userDAO = getUserDAO();
        try {
            List<User> list = userDAO.getAllUsers();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public User getUserById(long id) {
        UserDAO userDAO = getUserDAO();
        try {
            User user = userDAO.getUserById(id);
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean addUser(User user) {
        UserDAO userDAO = getUserDAO();
        try {
            return userDAO.addUser(user);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean editUser(User user) {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.editUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteUser(User user) {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.deleteUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void cleanUp() {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.dropTable();
        } catch (SQLException e) {

        }
    }

    public void createTable() {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.createTable();
        } catch (SQLException e) {

        }
    }
}
