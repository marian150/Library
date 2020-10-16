package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "name")
    private String name;

    public Genre() {}

    public Genre(Long genreId, String name) {
        this.genreId = genreId;
        this.name = name;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
