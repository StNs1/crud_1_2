package service;

import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateService implements UserService {

    private static SessionFactory sessionFactory = getSessionFactory();
    private static UserHibernateService userHibernateService;

    private UserHibernateService() {

    }

    public static UserHibernateService getInstance() {
        if (userHibernateService == null) {
            userHibernateService = new UserHibernateService();
        }
        return userHibernateService;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        DBHelper dbHelper = DBHelper.getInstance();
        Configuration configuration = dbHelper.getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            List<User> list = userHibernateDAO.getAllUsers();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(long id) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            return userHibernateDAO.getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserRole(String email) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            return userHibernateDAO.getUserRole(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            return userHibernateDAO.getUserByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isExistUser(String email, String password) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            return userHibernateDAO.isExistUser(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            userHibernateDAO.addUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editUser(User user) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            userHibernateDAO.editUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            userHibernateDAO.deleteUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   /* @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
       /* Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        return configuration;
    }*/

    @Override
    public void cleanUp() {
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());
        try {
            userHibernateDAO.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
