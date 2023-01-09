package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.recyclerAdapterAllBooks;
import com.example.librarymanagementsystem.adapter.recyclerAdapterReturns;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;

public class ReturnActivity extends AppCompatActivity implements recyclerAdapterReturns.DetailsListener {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private RecyclerView recyclerView;
    private recyclerAdapterReturns adapter;
    private SearchView searchView;
    private User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        bookList = DataHandling.getBookList();
        helperList = new ArrayList<>();
        if (getIntent().hasExtra("some_user")) {
            user = (User) getIntent().getSerializableExtra("some_user");
        }
        //helperList.addAll(DataHandling.getBookList());
        /*searchView = findViewById(R.id.searchView);
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
        });*/
        setBookInfo();
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
        System.out.println(helperList.size());
        adapter = new recyclerAdapterReturns(helperList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setBookInfo() {

        for(Book b: DataHandling.bookList) {
            for(BorrowingProcess bp: b.getBorrowers()) {
                System.out.println(bp.getUser().toString());
                if(bp.getUser().getId() == user.getId()) {
                    helperList.add(b);
                    System.out.println(b.getTitle());
                }
            }
        }
    }

    @Override
    public void onDetailClickExtend(int position) {

    }

    @Override
    public void onDetailClickReturn(int position) {

    }
}
