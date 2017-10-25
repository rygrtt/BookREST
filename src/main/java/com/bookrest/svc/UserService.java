package com.bookrest.svc;

import com.bookrest.dao.UserDao;
import com.bookrest.dao.jdbc.UserJdbcDao;
import com.bookrest.model.User;

public class UserService {

    private UserDao dao;

    public UserService() {
        dao = new UserJdbcDao();
    }

    public User find(String username, String password) {
        return dao.get(username, password);
    }
}
