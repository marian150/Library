package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class LoadBooksToBeArchivedModel {
    private SimpleStringProperty inv;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty year;
    private SimpleStringProperty isbn;
    private SimpleStringProperty notifId;

    public LoadBooksToBeArchivedModel(SimpleStringProperty inv, SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty year, SimpleStringProperty isbn, SimpleStringProperty notifId) {
        this.inv = inv;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.notifId = notifId;
    }

    public String getInv() {
        return inv.get();
    }

    public SimpleStringProperty invProperty() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv.set(inv);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getYear() {
        return year.get();
    }

    public SimpleStringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getNotifId() {
        return notifId.get();
    }

    public SimpleStringProperty notifIdProperty() {
        return notifId;
    }

    public void setNotifId(String notifId) {
        this.notifId.set(notifId);
    }
}
