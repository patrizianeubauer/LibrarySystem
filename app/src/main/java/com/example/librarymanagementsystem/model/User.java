package com.example.librarymanagementsystem.model;

import java.io.Serializable;

public class User implements Serializable {

    private String vorname;
    private String nachname;
    private String email;

    public User(String vorname, String nachname, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
