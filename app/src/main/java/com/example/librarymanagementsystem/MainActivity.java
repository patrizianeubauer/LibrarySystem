package com.example.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;
import com.example.librarymanagementsystem.ui.cardviews.AllBooksActivity;
import com.example.librarymanagementsystem.ui.cardviews.BorrowActivity;
import com.example.librarymanagementsystem.ui.cardviews.ReturnActivity;
import com.example.librarymanagementsystem.ui.cardviews.SearchActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CardView allBooks;
    CardView searchBooks;
    CardView returnBooks;
    CardView borrowingBooks;
    private AlertDialog dialog;
    TextView welcome;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("some_user")) {
            user = (User) getIntent().getSerializableExtra("some_user");
            welcome = findViewById(R.id.loggedInAs);
            welcome.setText("Welcome, "+user.getVorname()+" "+user.getNachname()+"!");
        }

        allBooks = findViewById(R.id.allBooks);
        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        searchBooks = findViewById(R.id.search);
        searchBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        returnBooks = findViewById(R.id.returns);
        returnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReturnActivity.class);
                intent.putExtra("some_user", user);
                startActivity(intent);
            }
        });

        borrowingBooks = findViewById(R.id.borrow);
        borrowingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BorrowActivity.class);
                intent.putExtra("some_user", user);
                startActivity(intent);
            }
        });
    }
}