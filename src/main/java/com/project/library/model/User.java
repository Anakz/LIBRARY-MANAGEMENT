package com.project.library.model;

import javax.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String cin;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String type;

    public User() {
    }
    public User(Long id, String firstName, String lastName, String cin, String email, String password, String phone, String address, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCin() {
        return cin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return ""+this.getFirstName() + " "+ this.getLastName();
    }
}
