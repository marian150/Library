package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SearchReaderTableView {

    private SimpleLongProperty id;
    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;
    private SimpleStringProperty regdate;
    private SimpleStringProperty rating;

    public SearchReaderTableView(SimpleLongProperty id, SimpleStringProperty fname, SimpleStringProperty lname,
                                 SimpleStringProperty email, SimpleStringProperty phone, SimpleStringProperty regdate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.regdate = regdate;

    }

    public Long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() { return id; }

    public void setId(Long id) {
        this.id.set(id);
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

    public String getRegdate() {
        return regdate.get();
    }

    public SimpleStringProperty regdateProperty() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate.set(regdate);
    }

    public String getRating() {
        return rating.get();
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }
}
