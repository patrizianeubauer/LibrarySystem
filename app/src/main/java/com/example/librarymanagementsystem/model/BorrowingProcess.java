package com.example.librarymanagementsystem.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BorrowingProcess implements Serializable {

    private User user;
    private Date dateOfIssue;
    private Date returnDate;
    private float fees;
    private int extensionCounter;

    public BorrowingProcess(User name, Date dateOfIssue) {
        this.user = name;
        this.dateOfIssue = dateOfIssue;
        Calendar cal = new GregorianCalendar();
        cal.setTime(dateOfIssue);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.returnDate = cal.getTime();
        if(daysBetween(this.returnDate, new Date()) > 0) this.fees = daysBetween(this.returnDate, new Date())*0.2f;
        else this.fees = 0.0f;

        if(this.fees < 0) this.fees = this.fees*(-1.0f);
        this.extensionCounter = 1;
    }

    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public int getExtensionCounter() {
        return extensionCounter;
    }

    public void incrementExtensionCounter() {
        this.extensionCounter = this.extensionCounter + 1;
    }

    public float getFees() {
        return fees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

}
