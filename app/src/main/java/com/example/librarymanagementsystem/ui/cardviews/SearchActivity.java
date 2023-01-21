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

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.recyclerAdapterAllBooks;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements recyclerAdapterAllBooks.DetailsListener {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private recyclerAdapterAllBooks adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookList = new ArrayList<>();
        helperList.addAll(DataHandling.bookList);

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
        helperList.addAll(DataHandling.bookList);
        ArrayList<Book> filteredList = new ArrayList<>();
        for(Book b:helperList) {
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
        adapter = new recyclerAdapterAllBooks(helperList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setBookInfo() {
        bookList = DataHandling.getBookList();
    }

    @Override
    public void onDetailClick(int position) {
        Intent intent = new Intent(SearchActivity.this, AllBooksDetailsActivity.class);
        intent.putExtra("some_detail", helperList.get(position));
        startActivity(intent);

        adapter.notifyDataSetChanged();
    }

}
