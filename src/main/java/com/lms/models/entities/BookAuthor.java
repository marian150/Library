package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "book_author")
public class BookAuthor {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "BOOK_AUTHOR_BOOK_FK"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "BOOK_AUTHOR_AUTHOR_FK"))
    private Author author;

    public BookAuthor () {}

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
