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

    public static void initListWithData() {
        bookList = new ArrayList<>();
        userList = new ArrayList<>();
        ArrayList<BorrowingProcess> names1 = new ArrayList<>();
        User u1 = new User("Herbert", "Fuchs", "h.f@gmx.at");
        User u2 = new User("Laura", "Hera", "hera.l@gmx.at");
        BorrowingProcess bp1 = new BorrowingProcess(u1, new Date());
        BorrowingProcess bp2 = new BorrowingProcess(u2, new Date());
        names1.add(bp1);
        names1.add(bp2);

        ArrayList<BorrowingProcess> names2 = new ArrayList<>();
        User u3 = new User("Simone", "Herre", "herre.simone@gmx.at");
        User u4 = new User("Eward", "Alle", "alleeward@gmx.at");

        Calendar c1 = new GregorianCalendar(2022, 02, 11);
        Calendar c2 = new GregorianCalendar(2022, 11, 23);
        names2.add(new BorrowingProcess(u3, c1.getTime()));
        names2.add(new BorrowingProcess(u4, c2.getTime()));

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        bookList.add(new Book("Title1", "ISBN1", "Author1", 3, 310, "Roman", "Location?", c1.getTime(), "Pub1", names1));
        bookList.add(new Book("Das Wunder", "ISBN12345", "Max Mustermann", 2,2180, "Thriller", "S2", c2.getTime(), "Veritas", names2));
        bookList.add(new Book("BOOK3", "ISBN3", "link", 1, 200, "Childrenbook", "Location?", c1.getTime(), "Pub3", new ArrayList<>()));
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void printAll() {
        for(Book b:bookList) {
            System.out.println(b.toString());
        }
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }
}
