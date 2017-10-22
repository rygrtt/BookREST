package com.bookrest.dao;

import com.bookrest.model.Note;

import java.util.List;

public interface NoteDao {
    public List<Note> getNotes(int id);
}
