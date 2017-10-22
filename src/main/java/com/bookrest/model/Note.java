package com.bookrest.model;

public class Note {
    private int bookId;
    private int noteId;
    private int pageNoteBegins;
    private int pageNoteEnds;
    private String text;

    public Note(int bookId, int noteId, int pageNoteBegins, int pageNoteEnds, String text) {
        this.bookId = bookId;
        this.noteId = noteId;
        this.pageNoteBegins = pageNoteBegins;
        this.pageNoteEnds = pageNoteEnds;
        this.text = text;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getPageNoteBegins() {
        return pageNoteBegins;
    }

    public void setPageNoteBegins(int pageNoteBegins) {
        this.pageNoteBegins = pageNoteBegins;
    }

    public int getPageNoteEnds() {
        return pageNoteEnds;
    }

    public void setPageNoteEnds(int pageNoteEnds) {
        this.pageNoteEnds = pageNoteEnds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
