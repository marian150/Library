package com.lms.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "return_book")
public class ReturnBook {
    @Id
    @Column(name = "return_id")
    private Long returnId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "lib_id")
    private Long libId;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @OneToOne(mappedBy = "returnBook")
    private RentBook rentBook;

    public ReturnBook() {}

    public ReturnBook(Long returnId, Long clientId, Long libId, Long bookId, LocalDate returnDate, RentBook rentBook) {
        this.returnId = returnId;
        this.clientId = clientId;
        this.libId = libId;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.rentBook = rentBook;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public RentBook getRentBook() {
        return rentBook;
    }

    public void setRentBook(RentBook rentBook) {
        this.rentBook = rentBook;
    }
}
