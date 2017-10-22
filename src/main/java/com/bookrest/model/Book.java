package com.bookrest.model;

public class Book {
    private int bookId;
    private int userId;

    public Book(int bookId, int userId, int yearPublished, String publisher, String edition, String title) {
        this.bookId = bookId;
        this.userId = userId;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
        this.edition = edition;
        this.title = title;
    }

    private int yearPublished;
    private String publisher;
    private String edition;
    private String title;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
