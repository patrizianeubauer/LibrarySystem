package com.example.librarymanagementsystem.data;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataHandling {
    public static ArrayList<Book> bookList;
    public static ArrayList<User> userList;
    public static User currentUser;

    public static void initListWithData() {
        bookList = new ArrayList<>();
        userList = new ArrayList<>();
        ArrayList<BorrowingProcess> names1 = new ArrayList<>();
        User u1 = new User("herb","Herbert", "Fuchs", "h.f@gmx.at", "Street3", "9020", "Klagenfurt", "12345");
        User u2 = new User("lau", "Laura", "Hera", "hera.l@gmx.at", "Street4", "8020", "Graz", "12345");
        BorrowingProcess bp1 = new BorrowingProcess(u1, new Date());
        BorrowingProcess bp2 = new BorrowingProcess(u2, new Date());
        names1.add(bp1);
        names1.add(bp2);

        ArrayList<BorrowingProcess> names2 = new ArrayList<>();
        User u3 = new User("sim","Simone", "Herre", "herre.simone@gmx.at", "Street1", "9020", "Klagenfurt", "12345");
        User u4 = new User("ed","Eward", "Alle", "alleeward@gmx.at","Street2", "8030", "Graz",  "12345");

        Calendar c1 = new GregorianCalendar(2022, 12, 23);
        Calendar c2 = new GregorianCalendar(2018, 11, 23);
        names2.add(new BorrowingProcess(u3, c1.getTime()));
        names2.add(new BorrowingProcess(u4, c2.getTime()));

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        bookList.add(new Book("A very long book title with more than 50 characters", "ISBN1", "Author1, Author2, Author3, Author4, Author5, Author5", 3, 310, "Roman", "S3", c1.getTime(), "Pub1", names1));
        bookList.add(new Book("Das Wunder", "ISBN12345", "Max Mustermann", 2,2180, "Thriller", "S2", c2.getTime(), "Veritas", names2));
        bookList.add(new Book("BOOK3", "ISBN3", "Hans Muster", 1, 200, "Childrenbook", "S1", c1.getTime(), "Pub3", new ArrayList<>()));
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        DataHandling.currentUser = currentUser;
    }

    public static void addNewUser(User newUser) {
        userList.add(newUser);
    }
}
