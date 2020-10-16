package com.lms.models.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookAuthorKey implements Serializable {
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "author_id")
    private Long authorId;

    public BookAuthorKey() {}

    public BookAuthorKey(Long bookId, Long authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BookAuthorKey that = (BookAuthorKey) o;
        return Objects.equals(bookId, that.bookId) && Objects.equals(authorId, that.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authorId);
    }
}
