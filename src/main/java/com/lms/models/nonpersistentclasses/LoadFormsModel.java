package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class LoadFormsModel {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private LocalDate date;
    private String status;


    public LoadFormsModel(String first_name, String last_name, String email, String phone, LocalDate date, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
