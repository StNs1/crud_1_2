package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean addUser(User user) throws SQLException {
        if (!isExistUser(user.getEmail(), user.getPassword())) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public void editUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public String getUserRole(String email) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        transaction.commit();
        session.close();
        return user.getRole();
    }

    @Override
    public boolean isExistUser(String email, String password) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.like("email", email))
                .add(Restrictions.like("password", password))
                .uniqueResult();
        transaction.commit();
        session.close();
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public User getUserById(long id) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.like("id", id))
                .uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void dropTable() throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User");
        transaction.commit();
        session.close();
    }
}
