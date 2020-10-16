package com.lms.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_state")
public class BookState {
    @Id
    @Column(name = "state_id")
    private Long stateId;

    @Column(name = "state_name")
    private String stateName;

    public BookState () {}

    public BookState(Long stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }
}
