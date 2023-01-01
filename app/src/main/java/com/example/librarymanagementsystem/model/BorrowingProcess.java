package com.example.librarymanagementsystem.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BorrowingProcess implements Serializable {

    private User user;
    private Date dateOfIssue;
    private Date returnDate;

    public BorrowingProcess(User name, Date dateOfIssue) {
        this.user = name;
        this.dateOfIssue = dateOfIssue;
        Calendar cal = new GregorianCalendar();
        cal.setTime(dateOfIssue);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        this.returnDate = cal.getTime();
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
