package com.lms.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "return_book")
public class ReturnBook {
    @Id
    @Column(name = "return_id")
    private Long returnId;

    private Long clientId;

    private Long libId;

    private Long bookId;

    @Column(name = "return_date")
    private LocalDate returnDate;

    public ReturnBook() {}

    public ReturnBook(Long returnId, Long clientId, Long libId, Long bookId, LocalDate returnDate) {
        this.returnId = returnId;
        this.clientId = clientId;
        this.libId = libId;
        this.bookId = bookId;
        this.returnDate = returnDate;
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
}
