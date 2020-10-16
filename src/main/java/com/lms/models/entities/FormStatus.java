package com.lms.models.entities;


import javax.persistence.*;

@Entity
@Table(name = "form_status")
public class FormStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "status_id")
    private Long statusId;
    @Column(name = "status_name")
    private String statusName;

    public FormStatus() {}

    public FormStatus(Long statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
