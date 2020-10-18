package com.lms.models.nonpersistentclasses;


import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class ReaderTableView {

     private SimpleStringProperty title;
     private SimpleStringProperty author;
     private SimpleStringProperty publisher;
     private SimpleStringProperty lenddate;
     private SimpleStringProperty duedate;

     public ReaderTableView(SimpleStringProperty title, SimpleStringProperty author, SimpleStringProperty publisher, SimpleStringProperty lenddate, SimpleStringProperty duedate) {
          this.title = title;
          this.author = author;
          this.publisher = publisher;
          this.lenddate = lenddate;
          this.duedate = duedate;
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

     public String getPublisher() {
          return publisher.get();
     }

     public SimpleStringProperty publisherProperty() {
          return publisher;
     }

     public void setPublisher(String publisher) {
          this.publisher.set(publisher);
     }

     public String getLenddate() {
          return lenddate.get();
     }

     public SimpleStringProperty lenddateProperty() {
          return lenddate;
     }

     public void setLenddate(String lenddate) {
          this.lenddate.set(lenddate);
     }

     public String getDuedate() {
          return duedate.get();
     }

     public SimpleStringProperty duedateProperty() {
          return duedate;
     }

     public void setDuedate(String duedate) {
          this.duedate.set(duedate);
     }
}
