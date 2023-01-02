package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.recyclerAdapterAllBooks;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AllBooksActivity extends AppCompatActivity implements recyclerAdapterAllBooks.DetailsListener, AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private ArrayList<Book> bookList;
    private recyclerAdapterAllBooks adapter;
    private Spinner spinner;
    String[] options = {"Title", "Available Copies", "Author", "Year Published"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.spinner);
        bookList = DataHandling.getBookList();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        Collections.sort(bookList, new SortbyTitle());
                        break;
                    case 1:
                        Collections.sort(bookList, new SortbyAvailableCopies());
                        break;
                    case 2:
                        Collections.sort(bookList, new SortbyAuthors());
                        break;
                    case 3:
                        Collections.sort(bookList, new SortbyYear());
                        break;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        setAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setAdapter() {
        adapter = new recyclerAdapterAllBooks(bookList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDetailClick(int position) {
        Intent intent = new Intent(AllBooksActivity.this, AllBooksDetailsActivity.class);
        intent.putExtra("some_detail", bookList.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(AllBooksActivity.this, "" + options[position] + " Selected..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    class SortbyTitle implements Comparator<Book> {
        public int compare(Book a, Book b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

    class SortbyAvailableCopies implements Comparator<Book> {
        public int compare(Book a, Book b){return a.getNumberAvailable() - b.getNumberAvailable();}
    }

    class SortbyAuthors implements Comparator<Book> {
        public int compare(Book a, Book b){return a.getAuthor().compareTo(b.getAuthor());}
    }

    class SortbyYear implements Comparator<Book> {
        public int compare(Book a, Book b){return a.getpublishingYear().compareTo(b.getpublishingYear());}
    }
}
