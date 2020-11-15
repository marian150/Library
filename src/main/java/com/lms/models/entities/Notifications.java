package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @SequenceGenerator(name = "notify_sequence", sequenceName = "notify_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="notify_sequence")
    @Column(name = "notify_id")
    private Long notifyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName= "book_id", foreignKey = @ForeignKey (name = "FK_BOOK_ID"))
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_id", referencedColumnName= "rent_id", foreignKey = @ForeignKey (name = "FK_RENT_ID"))
    private RentBook rentBook;

    @OneToOne
    @JoinColumn(name = "form_id", referencedColumnName= "form_id", foreignKey = @ForeignKey (name = "FK_FORM_ID"))
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName= "status_id", foreignKey = @ForeignKey (name = "FK_STATUS_ID"))
    private Status status;

    public Notifications() {}

    public Notifications(Long notifyId, Book book, RentBook rentBook, Form form, Status status) {
        this.notifyId = notifyId;
        this.book = book;
        this.rentBook = rentBook;
        this.form = form;
        this.status = status;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public RentBook getRentBook() {
        return rentBook;
    }

    public void setRentBook(RentBook rentBook) {
        this.rentBook = rentBook;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
