package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM db_user");
        ResultSet resultSet = stmt.getResultSet();
        List<User> list = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            list.add(user);
        }
        resultSet.close();
        stmt.close();
        return list;
    }

    public boolean addUser(User user) throws SQLException {
        String userEmail = user.getEmail();
        boolean flag = true;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT email FROM db_user");
        ResultSet resultSet = stmt.getResultSet();
        while (resultSet.next()) {
            String email = resultSet.getString(1);
            if (email.equals(userEmail)) {
                flag = false;
            }
        }
        resultSet.close();
        stmt.close();
        if (flag) {
            String insert = "INSERT INTO db_user(surname, name, password, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, userEmail);
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } else {
            return false;
        }
    }

    public void editUser(User user) throws SQLException {
        String edit = "UPDATE db_user SET surname = ?, name = ?, password = ?, email = ?, role = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(edit);
        preparedStatement.setString(1, user.getSurname());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getRole());
        preparedStatement.setLong(6, user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(User user) throws SQLException {
        String delete = "DELETE FROM db_user WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, user.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public User getUserById(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM db_user WHERE id ="+id);
        ResultSet resultSet = stmt.getResultSet();
        User user = null;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        }
        return user;
    }
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS db_user (id bigint auto_increment, surname varchar(256), name varchar(256), password varchar(256), email varchar(256), primary key (id))");
        stmt.close();
    }
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS db_user");
        stmt.close();
    }
}
