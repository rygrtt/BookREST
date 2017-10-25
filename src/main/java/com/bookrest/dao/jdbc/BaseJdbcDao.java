package com.bookrest.dao.jdbc;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

// abstract class defining database connection methods for all inheritors

public abstract class BaseJdbcDao {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BookTracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mysql";

    // Returns a database connection
    protected Connection getConnection() {
        Connection myConnection = null;

        try {
            Class.forName(DRIVER_NAME);
            myConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return myConnection;
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    protected void releaseResources(Connection conn, Statement stmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {

        }

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception ex) {

        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {

        }

    }
}
