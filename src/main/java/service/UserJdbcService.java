package service;

import dao.UserJdbcDAO;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcService implements UserService {

    private static UserJdbcService userJdbcService;

    private UserJdbcService() {

    }

    public static UserJdbcService getInstance() {
        if (userJdbcService == null) {
            userJdbcService = new UserJdbcService();
        }
        return userJdbcService;
    }

    /*private static Connection getMySqlConnection() {
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
        }*/
    private static UserJdbcDAO getUserDAO() {
        DBHelper dbHelper = DBHelper.getInstance();
        return new UserJdbcDAO(dbHelper.getConnection());
        //return new UserJdbcDAO(getMySqlConnection());
    }

    @Override
    public List<User> getAllUsers() {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            List<User> list = userJdbcDAO.getAllUsers();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User getUserById(long id) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            User user = userJdbcDAO.getUserById(id);
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String getUserRole(String email) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            return userJdbcDAO.getUserRole(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistUser(String email, String password) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            return userJdbcDAO.isExistUser(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            return userJdbcDAO.addUser(user);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            return userJdbcDAO.getUserByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean editUser(User user) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            userJdbcDAO.editUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(User user) {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            userJdbcDAO.deleteUser(user);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void cleanUp() {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            userJdbcDAO.dropTable();
        } catch (SQLException e) {

        }
    }

    public void createTable() {
        UserJdbcDAO userJdbcDAO = getUserDAO();
        try {
            userJdbcDAO.createTable();
        } catch (SQLException e) {

        }
    }
}
