package com.bookrest.model;

public class Citation {
    private String title;
    private String aFname;
    private String aLname;
    private String tFname;
    private String tLname;
    private String publisher;
    private String edition;
    private int yearPublished;
    private int pageNoteBegins;
    private int pageNoteEnds;

    public Citation(String title, String aFname, String aLname, String tFname, String tLname, String publisher, String edition, int yearPublished, int pageNoteBegins, int pageNoteEnds) {
        this.title = title;
        this.aFname = aFname;
        this.aLname = aLname;
        this.tFname = tFname;
        this.tLname = tLname;
        this.pageNoteBegins = pageNoteBegins;
        this.pageNoteEnds = pageNoteEnds;
        this.publisher = publisher;
        this.edition = edition;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getaFname() {
        return aFname;
    }

    public void setaFname(String aFname) {
        this.aFname = aFname;
    }

    public String getaLname() {
        return aLname;
    }

    public void setaLname(String aLname) {
        this.aLname = aLname;
    }

    public String gettFname() {
        return tFname;
    }

    public void settFname(String tFname) {
        this.tFname = tFname;
    }

    public String gettLname() {
        return tLname;
    }

    public void settLname(String tLname) {
        this.tLname = tLname;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }
}
