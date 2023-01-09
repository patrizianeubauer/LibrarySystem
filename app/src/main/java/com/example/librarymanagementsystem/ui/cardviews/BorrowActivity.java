package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.recyclerAdapterBorrow;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;
import java.util.Date;

public class BorrowActivity extends AppCompatActivity implements recyclerAdapterBorrow.DetailsListener {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private recyclerAdapterBorrow adapter;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helperList = new ArrayList<>();
        bookList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return false;
            }
        });
        setBookInfo();
        setAdapter();
    }

    private void filterList(String s) {
        ArrayList<Book> filteredList = new ArrayList<>();
        for(Book b:bookList) {
            if(b.getTitle().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(b);
            }
        }

        if(filteredList.isEmpty()) {
            Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
            adapter.setFilteredList(new ArrayList<>());
            this.helperList = filteredList;
        } else {
            adapter.setFilteredList(filteredList);
            this.helperList = filteredList;
        }
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
        adapter = new recyclerAdapterBorrow(bookList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setBookInfo() {
        boolean isBook = true;
        ArrayList<Book> list = DataHandling.getBookList();

        for(Book b:list) {
            isBook = true;
            if(b.isAvailable()) {
                for(BorrowingProcess u:b.getBorrowers()) {
                    if(u.getUser().equals(user)) {
                        isBook = false;
                    }
                }
                if(isBook) bookList.add(b);
            }
        }
    }

    @Override
    public void onDetailClick(int position) {
        if (getIntent().hasExtra("some_user")) {
            user = (User) getIntent().getSerializableExtra("some_user");

            for(int i = 0; i < DataHandling.bookList.size(); i++) {
                if(DataHandling.bookList.get(i).getTitle().equals(bookList.get(position).getTitle())) {
                    ArrayList<BorrowingProcess> bpList = DataHandling.bookList.get(i).getBorrowers();
                    bpList.add(new BorrowingProcess(user, new Date()));
                    DataHandling.bookList.get(i).setBorrowers(bpList);
                    //bookList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Borrowing was successful!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        Intent intent = new Intent(BorrowActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
