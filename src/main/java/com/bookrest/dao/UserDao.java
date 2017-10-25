package com.bookrest.dao;

import com.bookrest.model.User;

public interface UserDao extends DaoSupport<User>{
    User get (String userName, String password);
}
