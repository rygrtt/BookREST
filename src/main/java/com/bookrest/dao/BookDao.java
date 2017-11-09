package com.bookrest.dao;

import com.bookrest.model.Book;
import com.bookrest.model.Person;

import java.util.List;

public interface BookDao extends DaoSupport<Book> {
    public List<Book> getBooks(int id);
    public boolean insert(Book book, Person author, Person translator, int userId);
}
