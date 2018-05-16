package com.neo.dao.connection;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.Properties;

public class WrappedConnection implements AutoCloseable {
    private Connection connection;


    public WrappedConnection(Connection connection) {
        this.connection = connection;
    }

//    public WrappedConnection() {
//        try {
//            //ResourceBundle resource = ResourceBundle.getBundle("resource.database");
//            String url = "jdbc:mysql://localhost:3306/sampledatabase";
//            String user = "root";
//            String pass = "pass";
//            Properties prop = new Properties();
//            prop.put("user", user);
//            prop.put("password", pass);
//            connection = DriverManager.getConnection(url, prop);
//        } catch (MissingResourceException e) {
//            System.err.println("properties file is missing " + e);
//        } catch (SQLException e) {
//            System.err.println("not obtained connection " + e);
//        }
//    }

    public Statement createStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    public PreparedStatement prepareStatement(String s) throws SQLException {
        if (connection != null) {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            if (preparedStatement != null) {
                return preparedStatement;
            }
        }
        throw new SQLException("connection or preparedStatement is null");
    }

//    public void closePreparedStatement(PreparedStatement preparedStatement){
//        if (preparedStatement != null) {
//            try {
//                PreparedStatement.close();
//            } catch (SQLException e) {
//                System.err.println("statement is null " + e);
//            }
//        }
//    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("statement is null " + e);
            }
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}


