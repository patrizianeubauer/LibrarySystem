package com.example.librarymanagementsystem.data;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;
import java.util.Date;

public class DataHandling {
    public static ArrayList<Book> bookList;
    public static ArrayList<User> userList;

    public static void initListWithData() {
        bookList = new ArrayList<>();
        userList = new ArrayList<>();
        ArrayList<BorrowingProcess> names1 = new ArrayList<>();
        User u1 = new User("Herbert", "Fuchs", "h.f@gmx.at");
        User u2 = new User("Laura", "Hera", "hera.l@gmx.at");
        BorrowingProcess bp1 = new BorrowingProcess(u1, new Date(), 2.0f);
        BorrowingProcess bp2 = new BorrowingProcess(u2, new Date(), 6.0f);
        names1.add(bp1);
        names1.add(bp2);

        ArrayList<BorrowingProcess> names2 = new ArrayList<>();
        User u3 = new User("Simone", "Herre", "herre.simone@gmx.at");
        User u4 = new User("Eward", "Alle", "alleeward@gmx.at");

        names2.add(new BorrowingProcess(u3, new Date(), 2.0f));
        names2.add(new BorrowingProcess(u4, new Date(), 6.0f));

        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        userList.add(u4);

        bookList.add(new Book("Title1", "ISBN1", "Author1", 3, 310, "Roman", "Location?", new Date(), "Pub1", names1));
        bookList.add(new Book("Das Wunder", "ISBN12345", "Max Mustermann", 2,2180, "Thriller", "S2", new Date(), "Veritas", names2));
        bookList.add(new Book("BOOK3", "ISBN3", "link", 1, 200, "Childrenbook", "Location?", new Date(), "Pub3", new ArrayList<>()));
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void printAll() {
        for(Book b:bookList) {
            System.out.println(b.toString());
        }
    }

}
