package com.lms.models.nonpersistentclasses;

import java.util.List;
import java.util.Set;

public class LoadBooksToBeArchivedModel {
    private String inv;
    private String title;
    private String author;
    private String year;
    private String isbn;

    public LoadBooksToBeArchivedModel(String inv, String title, String author, String year, String isbn) {
        this.inv = inv;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public String getInv() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv = inv;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
