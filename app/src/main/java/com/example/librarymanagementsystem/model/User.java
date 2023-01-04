package com.example.librarymanagementsystem.model;

import java.io.Serializable;

public class User implements Serializable {

    private static int help = 0;
    private int id;
    private String vorname;
    private String nachname;
    private String email;

    public User(String vorname, String nachname, String email) {
        this.id = help++;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
