package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class FormTableView {

    private SimpleStringProperty first_name;
    private SimpleStringProperty last_name;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty date;
    private SimpleStringProperty status;

    public FormTableView(SimpleStringProperty first_name, SimpleStringProperty last_name,
                         SimpleStringProperty email, SimpleStringProperty phone,
                         SimpleStringProperty date, SimpleStringProperty status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.status = status;
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public SimpleStringProperty first_nameProperty() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name.set(first_name);
    }

    public String getLast_name() {
        return last_name.get();
    }

    public SimpleStringProperty last_nameProperty() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name.set(last_name);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
