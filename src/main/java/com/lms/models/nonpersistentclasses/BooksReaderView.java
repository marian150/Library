package com.lms.models.nonpersistentclasses;


public class BooksReaderView {

    private String title;
    private String author;
    private String publisher;
    private String lendDate;
    private String dueDate;

    public BooksReaderView(String title, String author, String publisher, String lendDate, String dueDate) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
