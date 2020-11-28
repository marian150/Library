package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class FormTableView {

    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty submitDate;
    private SimpleStringProperty status;
    private SimpleStringProperty notif_id;

    public FormTableView() {}

    public FormTableView(SimpleStringProperty fname, SimpleStringProperty lname,
                         SimpleStringProperty email, SimpleStringProperty phone,
                         SimpleStringProperty submitDate, SimpleStringProperty status, SimpleStringProperty notif_id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.submitDate = submitDate;
        this.status = status;
        this.notif_id = notif_id;
    }
    public FormTableView(SimpleStringProperty fname, SimpleStringProperty lname,
                         SimpleStringProperty email, SimpleStringProperty phone) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    public String getFname() {
        return fname.get();
    }

    public SimpleStringProperty fnameProperty() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname.set(fname);
    }

    public String getLname() {
        return lname.get();
    }

    public SimpleStringProperty lnameProperty() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname.set(lname);
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

    public String getSubmitDate() {
        return submitDate.get();
    }

    public SimpleStringProperty submitDateProperty() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate.set(submitDate);
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

    public String getNotif_id() {
        return notif_id.get();
    }

    public SimpleStringProperty notif_idProperty() {
        return notif_id;
    }

    public void setNotif_id(String notif_id) {
        this.notif_id.set(notif_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormTableView that = (FormTableView) o;
        return Objects.equals(fname, that.fname) &&
                Objects.equals(lname, that.lname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(submitDate, that.submitDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, email, phone, submitDate, status);
    }
}
