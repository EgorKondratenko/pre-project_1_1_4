package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static java.sql.JDBCType.BIGINT;
import static javax.swing.Action.NAME;
import static javax.swing.text.html.parser.DTDConstants.ID;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {   }


    @Override
    public void createUsersTable() { }

    @Override
    public void dropUsersTable() { }

    @Override
    public void saveUser(String name, String lastName, byte age) { }

    @Override
    public void removeUserById(long id) { }

    @Override
    public List<User> getAllUsers() { return null; }

    @Override
    public void cleanUsersTable() { }
}