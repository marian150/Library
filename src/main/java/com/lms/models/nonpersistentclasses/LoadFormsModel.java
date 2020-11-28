package com.lms.models.nonpersistentclasses;

import java.time.LocalDate;

public class LoadFormsModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate date;
    private String status;
    private Long notifId;


    public LoadFormsModel(String firstName, String lastName, String email, String phone, LocalDate date, String status, Long notifId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.status = status;
        this.notifId = notifId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNotifId() {
        return notifId;
    }

    public void setNotifId(Long notifId) {
        this.notifId = notifId;
    }
}
