package com.bookrest.dao.jdbc;

import com.bookrest.dao.UserDao;
import com.bookrest.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao extends BaseJdbcDao implements UserDao {

    private static final String SELECT_BY_NAME_PWD = "select * from User where userName = ? and userPw = ?";


    public User get(String userName, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        User user = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_BY_NAME_PWD);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                user = create(rs);
            }

            return user;

        } catch (Exception ex) {
            ex.printStackTrace();
            return user;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }

    public User get(int id) {
        return null;
    }

    public boolean insert(User user) {
        return false;
    }

    public List<User> getAll() {
        return null;
    }

    public boolean update(User user) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }

    private User create(ResultSet rs) throws SQLException {
        int userId = rs.getInt("userId");
        String userName = rs.getString("userName");
        String pw = rs.getString("userPw");

        return new User(userId, userName, pw);

    }
}
