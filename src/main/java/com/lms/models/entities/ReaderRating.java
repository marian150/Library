package com.lms.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reader_rating")
public class ReaderRating {
    @Id
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "rating")
    private int rating;

    public ReaderRating() {}

    public ReaderRating(Long ratingId, int rating) {
        this.ratingId = ratingId;
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
