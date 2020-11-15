package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class OverdueBooksTableView {
    private SimpleStringProperty rid;
    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleStringProperty phone;
    private SimpleStringProperty bid;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty dueDate;

    public OverdueBooksTableView(SimpleStringProperty rid, SimpleStringProperty fname, SimpleStringProperty lname, SimpleStringProperty phone, SimpleStringProperty bid, SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty dueDate) {
        this.rid = rid;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.bid = bid;
        this.title = title;
        this.author = author;
        this.dueDate = dueDate;
    }

    public String getRid() {
        return rid.get();
    }

    public SimpleStringProperty ridProperty() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid.set(rid);
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

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getBid() {
        return bid.get();
    }

    public SimpleStringProperty bidProperty() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid.set(bid);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public SimpleStringProperty dueDateProperty() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }
}
