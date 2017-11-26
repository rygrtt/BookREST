package com.bookrest.dao.jdbc;

import com.bookrest.dao.NoteDao;
import com.bookrest.model.Citation;
import com.bookrest.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteJdbcDao extends BaseJdbcDao implements NoteDao {

    private static final String SELECT_BY_BOOKID = "SELECT * FROM note WHERE bookId = ?";
    private static final String SELECT_BY_NOTEID = "SELECT * FROM note WHERE noteId = ?";
    private static final String INSERT_SQL = "INSERT INTO note(bookId, pageNoteBegins, pageNoteEnds, text) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CITATION =
            "SELECT noteId, pageNoteBegins, pageNoteEnds, publisher, yearPublished, edition, title, " +
                    "a.firstname AS afirstname, a.lastname AS alastname, t.firstname AS tfirstname, t.lastname AS tlastname\n" +
                    "FROM note AS n, book AS b, author AS a, translator AS t\n" +
                    "WHERE n.bookId = b.bookId\n" +
                    "AND a.authorId = b.authorId\n" +
                    "AND t.translatorId = b.translatorId\n" +
                    "AND noteId = ?";

    public List<Note> getNotes(int bookId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Note> result = new ArrayList<Note>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_BY_BOOKID);
            stmt.setInt(1, bookId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Note note = create(rs);
                result.add(note);
            }

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }


    public Note get(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Note note = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_BY_NOTEID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            while (rs.next()) {
                note = create(rs);
            }

            return note;

        } catch (Exception ex) {
            ex.printStackTrace();
            return note;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }

    public Citation getCitation(int noteId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Citation citation = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SELECT_CITATION);
            stmt.setInt(1, noteId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                citation = createCitation(rs);
            }

            return citation;

        } catch (Exception ex) {
            ex.printStackTrace();
            return citation;
        } finally {
            releaseResources(conn, stmt, rs);
        }
    }

    public boolean insert(Note note) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean success = false;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT_SQL);
            stmt.setInt(1, note.getBookId());
            stmt.setInt(2, note.getPageNoteBegins());
            stmt.setInt(3, note.getPageNoteEnds());
            stmt.setString(4, note.getText());
            stmt.executeUpdate();

            success = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseResources(conn, stmt, rs);
        }

        return success;
    }

    public List<Note> getAll() {
        return null;
    }

    public boolean update(Note note) {
        return false;
    }

    public boolean delete(String id) {
        return false;
    }

    private Note create(ResultSet rs) throws SQLException {

        int bookId = rs.getInt("bookId");
        int noteId = rs.getInt("noteId");
        int pageNoteBegins = rs.getInt("pageNoteBegins");
        int pageNoteEnds = rs.getInt("pageNoteEnds");
        String text = rs.getString("text");

        return new Note(bookId, noteId, pageNoteBegins, pageNoteEnds, text);
    }

    private Citation createCitation(ResultSet rs) throws SQLException {

        int pageNoteBegins = rs.getInt("pageNoteBegins");
        int pageNoteEnds = rs.getInt("pageNoteEnds");
        int yearPublished = rs.getInt("yearPublished");

        String publisher = rs.getString("publisher");
        String edition = rs.getString("edition");
        String title = rs.getString("title");
        String aFname = rs.getString("afirstname");
        String aLname = rs.getString("alastname");
        String tFname = rs.getString("tfirstname");
        String tLname = rs.getString("tlastname");

        return new Citation(title, aFname, aLname, tFname, tLname,
                publisher, edition, yearPublished, pageNoteBegins, pageNoteEnds);
    }

}
