package com.example.librarymanagementsystem.model;

import java.io.Serializable;

public class User implements Serializable {

    private static int help = 0;
    private int id;
    private String vorname;
    private String nachname;
    private String email;
    private String username;
    private String password;
    private String street;
    private String zipcode;
    private String city;

    public User(String username, String vorname, String nachname, String email, String street, String zipcode, String city, String password) {
        this.id = help++;
        this.username = username;
        this.password = password;
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
