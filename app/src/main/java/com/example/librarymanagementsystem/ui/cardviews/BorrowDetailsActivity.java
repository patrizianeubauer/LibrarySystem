package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

import java.util.ArrayList;

public class BorrowDetailsActivity extends AppCompatActivity implements recyclerAdapterBorrow.DetailsListener {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private recyclerAdapterBorrow adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        adapter = new recyclerAdapterBorrow(helperList, this);
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
        Intent intent = new Intent(BorrowDetailsActivity.this, MainActivity.class);
        intent.putExtra("some_detail", helperList.get(position));
        startActivity(intent);

        adapter.notifyDataSetChanged();
    }
}
