package com.example.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.librarymanagementsystem.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CardView allBooks;
    CardView searchBooks;
    CardView returnBooks;
    CardView borrowingBooks;
    CardView addNewUser;
    private AlertDialog dialog;
    Button registerButton;
    TextView welcome;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DataHandling.initListWithData();

        user = DataHandling.getCurrentUser();
        welcome = findViewById(R.id.loggedInAs);
        welcome.setText("Welcome, " + user.getVorname() + " " + user.getNachname() + "!");

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
                startActivity(intent);
            }
        });

        borrowingBooks = findViewById(R.id.borrow);
        borrowingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BorrowActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}