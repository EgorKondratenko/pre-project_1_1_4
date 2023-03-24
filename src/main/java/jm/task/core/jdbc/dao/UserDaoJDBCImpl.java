package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// все исключения пиши сюда
public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        Connection connection = Util.getConnection();

        String stringSql = "CREATE TABLE IF NOT EXISTS USERS "
                + "(ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "NAME VARCHAR(45) NOT NULL, "
                + "LASTNAME VARCHAR(45) NOT NULL, "
                + "AGE TINYINT NOT NULL);";

        try (PreparedStatement  preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void dropUsersTable() {
        Connection connection = Util.getConnection();

        String stringSql = "DROP TABLE IF EXISTS USERS;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();

        String stringSql = "INSERT INTO USERS (name, lastname, age) VALUES(?, ?, ?)";

        try (PreparedStatement  preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.format("User с именем - %s  добавлен в базу данных\n", name);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Connection connection = Util.getConnection();

        String stringSql = "DELETE FROM USERS WHERE ID";
        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();

        List <User> userList = new ArrayList<>();
        String stringSql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery(stringSql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Connection connection = Util.getConnection();

        String stringSql = "TRUNCATE TABLE USERS";
        try (PreparedStatement preparedStatement = connection.prepareStatement(stringSql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
}
