package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String DB_HOST = "localhost";
    private final static String DB_PORT = "3306";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "root";
    private final static String DB_NAME = "dbusers_schema";
    public static Connection getConnection() {

        String connections = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

        try {
            return DriverManager.getConnection(connections, DB_USER, DB_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
