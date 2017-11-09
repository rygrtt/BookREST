package com.bookrest.svc;

import com.bookrest.dao.BookDao;
import com.bookrest.dao.jdbc.BookJdbcDao;
import com.bookrest.model.Book;
import com.bookrest.model.Person;

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

    public boolean insert(Book book, Person author, Person translator, int userId) {
        return dao.insert(book, author, translator, userId);
    }
}
