package com.lms.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rent_book")
public class RentBook {
    @Id
    @Column(name = "rent_id")
    private Long rentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey (name = "RENT_BOOK_USER_FKV2"))
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey (name = "RENT_BOOK_USER_FK"), insertable = false, updatable = false)
    private User librarian;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "RENT_BOOK_BOOK_FK"))
    private Book book;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    public RentBook() {}

    public RentBook(Long rentId, User client, User librarian,
                    Book book, LocalDate rentDate, LocalDate dueDate) {
        this.rentId = rentId;
        this.client = client;
        this.librarian = librarian;
        this.book = book;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
