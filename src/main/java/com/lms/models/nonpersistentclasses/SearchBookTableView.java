package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class SearchBookTableView {
    private SimpleStringProperty inventoryNumber;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty isbn;
    private SimpleStringProperty genre;
    private SimpleStringProperty publisher;
    private SimpleStringProperty year;
    private SimpleStringProperty state;

    public SearchBookTableView(SimpleStringProperty inventoryNumber, SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty isbn, SimpleStringProperty publisher, SimpleStringProperty year, SimpleStringProperty genre, SimpleStringProperty state) {
        this.inventoryNumber = inventoryNumber;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publisher = publisher;
        this.year = year;
        this.state = state;
    }

    public String getInventoryNumber() {
        return inventoryNumber.get();
    }

    public SimpleStringProperty inventoryNumberProperty() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber.set(inventoryNumber);
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

    public String getIsbn() {
        return isbn.get();
    }

    public SimpleStringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
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

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }
}
