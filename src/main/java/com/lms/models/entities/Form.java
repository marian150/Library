package com.lms.models.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "form")
public class Form {
    @Id
    @SequenceGenerator(name = "form_sequence", sequenceName = "form_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="form_sequence")
    @Column(name = "form_id")
    private Long formId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "submit_date")
    private LocalDate submitDate;
/*
    @ManyToOne()
    @JoinColumn(name = "status_id", foreignKey = @ForeignKey(name = "FORM_FORM_STATUS_FK" ))
    private Status formStatus;
*/
    public Form() {}

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }
/*
    public Status getFormStatus() {
        return formStatus;
    }

    public void setFormStatus(Status formStatus) {
        this.formStatus = formStatus;
    }

 */
}
