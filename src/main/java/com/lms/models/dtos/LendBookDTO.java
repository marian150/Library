package com.lms.models.dtos;


import java.util.List;

public class LendBookDTO {
    private Long userID;
    private List<Long> bookIDs;

    public LendBookDTO() {}

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public List<Long> getBookIDs() {
        return bookIDs;
    }

    public void setBookIDs(List<Long> bookIDs) {
        this.bookIDs = bookIDs;
    }
}
