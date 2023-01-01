package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.MainActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.adapter.recyclerAdapterReturns;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.util.ArrayList;

public class ReturnDetailsActivity extends AppCompatActivity implements recyclerAdapterReturns.DetailsListener {

    private ArrayList<User> userList;
    private RecyclerView recyclerView;
    private recyclerAdapterReturns adapter;
    private Book book;
    private ArrayList<Book> bookList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("some_book")) {
            book = (Book) getIntent().getSerializableExtra("some_book");
        }

        bookList = DataHandling.getBookList();

        userList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
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
        adapter = new recyclerAdapterReturns(userList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setBookInfo() {
        for (BorrowingProcess bp : book.getBorrowers()) {
            userList.add(bp.getUser());
        }
    }

    @Override
    public void onDetailClick(int position) {
        for(int i = 0; i < bookList.size(); i++) {
            if(bookList.get(i).getTitle().equals(book.getTitle())) {
                for (BorrowingProcess bp : book.getBorrowers()) {
                    if (bp.getUser().getNachname().equals(userList.get(position).getNachname()) && bp.getUser().getVorname().equals(userList.get(position).getVorname())) {
                        bookList.get(i).removeABP(bp);
                        if(bookList.get(i).getBorrowers().size() < bookList.get(i).getNumberAvailable()) bookList.get(i).setAvailable(true);
                        DataHandling.printAll();
                        adapter.notifyDataSetChanged(); break;
                    }
                }
            }
        }

        Intent intent = new Intent(ReturnDetailsActivity.this, MainActivity.class);
        Toast.makeText(ReturnDetailsActivity.this, "Returning was successful!", Toast.LENGTH_SHORT).show();

        startActivity(intent);
        adapter.notifyDataSetChanged();
    }
}
