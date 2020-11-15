package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class FormTableView {

    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty submitDate;
    private SimpleStringProperty status;

    public FormTableView(SimpleStringProperty fname, SimpleStringProperty lname,
                         SimpleStringProperty email, SimpleStringProperty phone,
                         SimpleStringProperty submitDate, SimpleStringProperty status) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.submitDate = submitDate;
        this.status = status;
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
}
