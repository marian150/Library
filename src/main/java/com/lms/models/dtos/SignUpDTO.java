package com.lms.models.dtos;

public class SignUpDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;

    public SignUpDTO(){}

    public SignUpDTO(String firstname, String lastname, String email, String password, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
