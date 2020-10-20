package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "reader_rating")
public class ReaderRating {
    @Id
    @Column(name = "rating_id")
    private Long ratingId;

    @Column(name = "rating")
    private int rating;
/*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating_id")
    @MapsId
    private User user;
*/
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
