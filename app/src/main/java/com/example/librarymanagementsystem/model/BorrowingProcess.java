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

    public BorrowingProcess(User name, Date dateOfIssue, float fees) {
        this.user = name;
        this.dateOfIssue = dateOfIssue;
        Calendar cal = new GregorianCalendar();
        cal.setTime(dateOfIssue);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.returnDate = cal.getTime();
        this.fees = fees;
        this.extensionCounter = 1;
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

    public void setFees(float fees) {
        this.fees = fees;
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
