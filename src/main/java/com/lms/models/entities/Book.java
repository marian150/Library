package com.lms.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "BOOK_BOOK_STATE_FK"))
    private BookState bookState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", foreignKey = @ForeignKey(name = "BOOK_PUBLISHER_FK"))
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_id", foreignKey = @ForeignKey(name = "BOOK_BOOK_COVERS_FK"))
    private BookCovers bookCovers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", foreignKey = @ForeignKey(name = "BOOK_GENRE_FK"))
    private Genre genre;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Book_author",
            joinColumns = {
                    @JoinColumn(name = "book_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author_id")
            }
    )
    private Set<Author> authors = new HashSet<>();

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    public Book () {}

    public Book(Long bookId, BookState bookState, Publisher publisher, BookCovers bookCovers,
                Genre genre, String title, Set<Author> authors, String isbn, LocalDate issueDate) {
        this.bookId = bookId;
        this.bookState = bookState;
        this.publisher = publisher;
        this.bookCovers = bookCovers;
        this.genre = genre;
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.issueDate = issueDate;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BookCovers getBookCovers() {
        return bookCovers;
    }

    public void setBookCovers(BookCovers bookCovers) {
        this.bookCovers = bookCovers;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
