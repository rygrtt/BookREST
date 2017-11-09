package com.bookrest.dao.jdbc;

import com.bookrest.dao.BookDao;
import com.bookrest.model.Book;
import com.bookrest.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookJdbcDao extends BaseJdbcDao implements BookDao {

    private static final String SELECT_ALL = "select * from book";

    private static final String DEL_SQL = "delete from book where bookID = ?";

    private static final String SELECT_BY_ID = "select * from book where userId = ?";

    private static final String UPDATE_SQL = "update book set title = ?, yearPublished = ?, publisher = ?, edition = ?";

    private static final String INSERT_AUTHOR = "insert into author (firstName, lastName) values (?, ?)";

    private static final String INSERT_TRANSLATOR = "insert into translator (firstName, lastName) values (?, ?)";

    private static final String INSERT_BOOK = "insert into book (title, publisher, edition, yearPublished, userId, translatorId, authorId) values(?, ?, ?, ?, ?, ?, ?)";


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

    // Since this is a test app, we're not checking to see if an author/translator already exists in the db,
    // we're just living with duplicates
    public boolean insert(Book book, Person author, Person translator, int userId) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean success;

        try {
            // turning off autocommit to create transaction
            conn = getConnection();
            conn.setAutoCommit(false);
            // ensure we get the new keys back for later insertions
            stmt = conn.prepareStatement(INSERT_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, author.getFirstName());
            stmt.setString(2, author.getLastName());

            stmt.executeUpdate();
            int authorId = 0;

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                authorId = rs.getInt(1);
            }

            int translatorId = 0;

            if (translator != null) {
                stmt = conn.prepareStatement(INSERT_TRANSLATOR, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, translator.getFirstName());
                stmt.setString(2, translator.getLastName());
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    translatorId = rs.getInt(1);
                }

            }

            stmt = conn.prepareStatement(INSERT_BOOK);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getPublisher());
            stmt.setString(3, book.getEdition());
            stmt.setInt(4, book.getYearPublished());
            stmt.setInt(5, userId);
            stmt.setInt(7, authorId);

            if (translatorId != 0) {
                stmt.setInt(6, translatorId);
            }

            stmt.executeUpdate();

            conn.commit();
            success = true;

        } catch (SQLException e) {
            success = false;
                try {
                    e.printStackTrace();
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
        } finally {
            releaseResources(conn, stmt, rs);
        }

        return success;
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