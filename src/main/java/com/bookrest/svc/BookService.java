package com.bookrest.svc;

import com.bookrest.dao.BookDao;
import com.bookrest.dao.jdbc.BookJdbcDao;
import com.bookrest.model.Book;

import java.util.List;

public class BookService {
    private BookDao dao;

    public BookService () {
        dao = new BookJdbcDao();
    }

    public List<Book> getAll() {
        return dao.getAll();
    }

    public List<Book> getBooks(int userId) {
        return dao.getBooks(userId);
    }
}
