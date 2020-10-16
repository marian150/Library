package com.lms.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "BookAuthor")
@Table(name = "book_author")
public class BookAuthor {
    @EmbeddedId
    private BookAuthorKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    //@JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "BOOK_AUTHOR_BOOK_FK"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorId")
    //@JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "BOOK_AUTHOR_AUTHOR_FK"))
    private Author author;

    public BookAuthor () {}

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
        this.id = new BookAuthorKey(book.getBookId(), author.getAuthorId());
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

    public BookAuthorKey getId() {
        return id;
    }

    public void setId(BookAuthorKey id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookAuthor that = (BookAuthor) o;
        return Objects.equals(book, that.book) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, author);
    }
}
