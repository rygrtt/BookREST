package com.bookrest.dao.jdbc;

import com.bookrest.dao.NoteDao;
import com.bookrest.model.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteJdbcDao extends BaseJdbcDao implements NoteDao {

    private static final String SELECT_BY_BOOKID = "select * from note where bookId = ?";
    private static final String SELECT_BY_NOTEID = "select * from note where noteId = ?";

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

    public boolean insert(Note note) {
        return false;
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

    private Note create(ResultSet rs) throws SQLException{

        int bookId = rs.getInt("bookId");
        int noteId = rs.getInt("noteId");
        int pageNoteBegins = rs.getInt("pageNoteBegins");
        int pageNoteEnds = rs.getInt("pageNoteEnds");
        String text = rs.getString("text");

        return new Note(bookId, noteId, pageNoteBegins, pageNoteEnds, text);
    }

}
