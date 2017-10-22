package com.bookrest.dao.jdbc;

import com.bookrest.dao.BookDao;
import com.bookrest.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookJdbcDao extends BaseJdbcDao implements BookDao {

    private static final String SELECT_ALL = "select * from book";

    private static final String DEL_SQL = "delete from book where bookID = ?";

    private static final String SELECT_BY_ID = "select * from book where userId = ?";

    private static final String UPDATE_SQL = "update book set title = ?, yearPublished = ?, publisher = ?, edition = ?";

    private static final String INSERT_SQL = "insert into book (bookId, ";


    public List<Book> getBooks(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Book> result = new ArrayList<Book>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = create(rs);
                result.add(book);
            }

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }

    public boolean insert(Book book) {
        return false;
    }

    public Book get(int id) {
        return null;
    }

    public List<Book> getAll() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Book> result = new ArrayList<Book>();

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL);

            while (rs.next()) {
                Book book = create(rs);
                result.add(book);
            }

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }

    public boolean update(Book book) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }


    private Book create(ResultSet rs) throws SQLException {

        int bookId = rs.getInt("bookId");
        int userId = rs.getInt("userId");
        int yearPublished = rs.getInt("yearPublished");
        String publisher = rs.getString("publisher");
        String title = rs.getString("title");
        String edition = rs.getString("edition");

        return new Book(bookId, userId, yearPublished, publisher, edition, title);
    }
}