package com.bookrest.dao;

import com.bookrest.model.Citation;
import com.bookrest.model.Note;

import java.util.List;

public interface NoteDao extends DaoSupport<Note>{
    public List<Note> getNotes(int id);
    public Citation getCitation(int id);
}
