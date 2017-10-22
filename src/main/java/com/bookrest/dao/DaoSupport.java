package com.bookrest.dao;

import java.util.List;

public interface DaoSupport<T> {
    T get(int id);
    boolean insert(T t);
    List<T> getAll();
    boolean update(T t);
    boolean delete(String id);
}
