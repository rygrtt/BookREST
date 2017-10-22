package com.bookrest.dao;

import com.bookrest.model.Book;

import java.util.List;

public interface BookDao extends DaoSupport<Book> {
    public List<Book> getBooks(int id);
}
