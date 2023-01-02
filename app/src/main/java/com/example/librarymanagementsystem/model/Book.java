package com.example.librarymanagementsystem.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book implements Serializable {
    private String title;
    private String isbn;
    private String author;
    private int numberAvailable;
    private boolean available;
    private Date publishingYear;
    private String publisher;
    private int numberOfPages;
    private String genre;
    private String location;
    private ArrayList<BorrowingProcess> borrowers;

    public Book(String title, String isbn, String author, int numberAvailable, int numberOfPages, String genre, String location, Date publishingYear, String publisher, ArrayList<BorrowingProcess> borrowers) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.numberAvailable = numberAvailable;
        this.borrowers = borrowers;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
        this.location = location;
        this.publishingYear = publishingYear;
        this.publisher = publisher;

        if(this.borrowers.size() < numberAvailable) {
            this.available = true;
        } else {
            this.available = false;
        }
    }

    public Date getpublishingYear() {
        return publishingYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberAvailable() {
        return numberAvailable;
    }

    public ArrayList<BorrowingProcess> getBorrowers() {
        return borrowers;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void removeABP(BorrowingProcess bp) {

        for(int i = 0; i < borrowers.size(); i++) {
            if(borrowers.get(i).getUser().getNachname().equals(bp.getUser().getNachname())) {
                borrowers.remove(i);
            }
        }
    }

    public void incrementCounterinABP(BorrowingProcess bp) {

        for(int i = 0; i < borrowers.size(); i++) {
            if(borrowers.get(i).getUser().getNachname().equals(bp.getUser().getNachname())) {
                borrowers.get(i).incrementExtensionCounter();
            }
        }
    }

    public void updateReturnDate(BorrowingProcess bp) {
        for(int i = 0; i < borrowers.size(); i++) {
            if(borrowers.get(i).getUser().getNachname().equals(bp.getUser().getNachname())) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_MONTH, 14);
                borrowers.get(i).setReturnDate(cal.getTime());
            }
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", numberAvailable=" + numberAvailable +
                ", available=" + available +
                ", publishingYear=" + publishingYear +
                ", publisher='" + publisher + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", genre='" + genre + '\'' +
                ", location='" + location + '\'' +
                ", borrowers=" + borrowers.size() +
                '}';
    }
}
