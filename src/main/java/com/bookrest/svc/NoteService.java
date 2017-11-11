package com.bookrest.svc;

import com.bookrest.dao.NoteDao;
import com.bookrest.dao.jdbc.NoteJdbcDao;
import com.bookrest.model.Citation;
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

    public Note getNote(int noteId) {
        return dao.get(noteId);
    }

    public boolean insert(Note note) {
        return dao.insert(note);
    }

    public Citation getCitation(int noteId) {
        return dao.getCitation(noteId);
    }
}
