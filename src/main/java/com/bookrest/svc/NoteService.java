package com.bookrest.svc;

import com.bookrest.dao.NoteDao;
import com.bookrest.dao.jdbc.NoteJdbcDao;
import com.bookrest.model.Note;

import java.util.List;

public class NoteService {

    private NoteDao dao;

    public NoteService () {
        dao = new NoteJdbcDao();
    }

    public List<Note> getNotes(int bookId) {
        return dao.getNotes(bookId);
    }
}
