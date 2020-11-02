package com.lms.models.dtos;

import java.util.List;

public class ReturnBookDTO {
    private List<Long> rentBookIds;
    private Long libId;

    public ReturnBookDTO(){}
    public List<Long> getBookIds() {
        return rentBookIds;
    }

    public void setBookIds(List<Long> rentBookIds) {
        this.rentBookIds = rentBookIds;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }
}
