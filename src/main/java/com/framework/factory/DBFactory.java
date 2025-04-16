package com.framework.factory;

import org.slf4j.Logger;
import com.framework.reporting.TestLog;

import java.sql.*;

public class DBFactory {

    public static Connection stageConnection, prodConnection, integConnection;
    public static Statement stmt = null;
    static ResultSet rs = null;
    public Connection connection;
    Logger log = org.slf4j.LoggerFactory.getLogger("DBFactory");

    public DBFactory(String DB_URL, String DB_User, String DB_Password, String connectionType) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (connectionType.equals("stage"))
                stageConnection = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
            else if (connectionType.equals("prod"))
                prodConnection = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
            else if (connectionType.equals("integ"))
                integConnection = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet selectQuery(String query, Connection connection) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            TestLog.stepInfo("SQL exception occurred while executing the query: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static ResultSet updateQuery(String query, Connection connection) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public DBFactory(String DB_URL, String DB_User, String DB_Password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_User, DB_Password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection connectDb(String DB_URL, String DB_User, String DB_Password) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_User, DB_Password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
