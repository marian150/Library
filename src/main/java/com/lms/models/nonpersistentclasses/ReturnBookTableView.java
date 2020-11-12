package com.lms.models.nonpersistentclasses;

import javafx.beans.property.SimpleStringProperty;

public class ReturnBookTableView {
    private SimpleStringProperty lendId;
    private SimpleStringProperty readerId;
    private SimpleStringProperty inv;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleStringProperty lendDate;
    private SimpleStringProperty dueDate;
    private SimpleStringProperty operator;
    private SimpleStringProperty type;

    public ReturnBookTableView( SimpleStringProperty lendId, SimpleStringProperty readerId, SimpleStringProperty inv, SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty lendDate, SimpleStringProperty dueDate, SimpleStringProperty operator, SimpleStringProperty type) {
        this.lendId = lendId;
        this.readerId = readerId;
        this.inv = inv;
        this.title = title;
        this.author = author;
        this.lendDate = lendDate;
        this.dueDate = dueDate;
        this.operator = operator;
        this.type = type;
    }

    public String getLendId() {
        return lendId.get();
    }

    public SimpleStringProperty lendIdProperty() {
        return lendId;
    }

    public void setLendId(String lendId) {
        this.lendId.set(lendId);
    }

    public String getReaderId() {
        return readerId.get();
    }

    public SimpleStringProperty readerIdProperty() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId.set(readerId);
    }

    public String getInv() {
        return inv.get();
    }

    public SimpleStringProperty invProperty() {
        return inv;
    }

    public void setInv(String inv) {
        this.inv.set(inv);
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

    public String getLendDate() {
        return lendDate.get();
    }

    public SimpleStringProperty lendDateProperty() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate.set(lendDate);
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

    public String getOperator() {
        return operator.get();
    }

    public SimpleStringProperty operatorProperty() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator.set(operator);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
