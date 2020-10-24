package com.lms.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @SequenceGenerator(name = "publisher_sequence", sequenceName = "publisher_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="publisher_sequence")
    @Column(name = "publisher_id")
    private Long publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    public Publisher() {}

    public Publisher(Long publisherId, String publisherName) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
