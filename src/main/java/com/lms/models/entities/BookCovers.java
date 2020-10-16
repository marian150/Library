package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "book_covers")
public class BookCovers {
    @Id
    @Column(name = "cover_id")
    private Long coverId;

    @Column(name = "cover_name")
    private String coverName;

    public BookCovers() {}

    public BookCovers(Long coverId, String coverName) {
        this.coverId = coverId;
        this.coverName = coverName;
    }

    public Long getCoverId() {
        return coverId;
    }

    public void setCoverId(Long coverId) {
        this.coverId = coverId;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }
}
