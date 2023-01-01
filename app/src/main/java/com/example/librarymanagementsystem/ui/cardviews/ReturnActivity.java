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
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;

import java.util.ArrayList;

public class ReturnActivity extends AppCompatActivity implements recyclerAdapterAllBooks.DetailsListener {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private RecyclerView recyclerView;
    private recyclerAdapterAllBooks adapter;
    private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        bookList = DataHandling.getBookList();
        helperList = new ArrayList<>();
        //helperList.addAll(DataHandling.getBookList());
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
        adapter.notifyDataSetChanged();
    }

    private void filterList(String s) {

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
        adapter.notifyDataSetChanged();
    }

    private void setBookInfo() {
        helperList.addAll(DataHandling.bookList);
        ArrayList<Book> returningList = new ArrayList<>();

        for(Book b: helperList) {
            if(b.getBorrowers().size() > 0) {
                returningList.add(b);
            }
        }

        helperList.clear();
        helperList.addAll(returningList);
    }

    @Override
    public void onDetailClick(int position) {
        Intent intent = new Intent(ReturnActivity.this, ReturnDetailsActivity.class);
        intent.putExtra("some_book", helperList.get(position));
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }
}
