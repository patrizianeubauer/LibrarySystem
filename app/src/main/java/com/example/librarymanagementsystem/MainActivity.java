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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    CardView allBooks, searchBooks, returnBooks, borrowingBooks, addingNewBook;
    private AlertDialog dialog;
    TextView welcome;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        addingNewBook = findViewById(R.id.addNewBook);
        addingNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new book:");
        View viewAddDialog = getLayoutInflater().inflate(R.layout.add_new_book_dialog, null);
        EditText eTitle = viewAddDialog.findViewById(R.id.title);
        EditText eISBN = viewAddDialog.findViewById(R.id.isbn);
        EditText eAuthor = viewAddDialog.findViewById(R.id.author);
        EditText eNOP = viewAddDialog.findViewById(R.id.numberOfPages);
        Spinner sQuantity = viewAddDialog.findViewById(R.id.spinnerQuantity);
        Spinner sGenre = viewAddDialog.findViewById(R.id.spinnerGenre);
        Spinner sLocation = viewAddDialog.findViewById(R.id.spinnerLocation);
        Spinner sPublishingYear = viewAddDialog.findViewById(R.id.spinnerPublishingYear);
        EditText ePublisher = viewAddDialog.findViewById(R.id.publisher);
        Button bAdd, bCancel;
        bAdd = viewAddDialog.findViewById(R.id.addButton);
        bCancel = viewAddDialog.findViewById(R.id.cancelButton);

        bAdd.setOnClickListener(view -> {
            String title = eTitle.getText().toString();
            String isbn = eISBN.getText().toString();
            String author = eAuthor.getText().toString();
            String location = sLocation.getSelectedItem().toString();
            String publisher = ePublisher.getText().toString();

            if (title.equals("") || isbn.equals("") || eNOP.getText().toString().equals("") || author.equals("") || location.equals("") || publisher.equals("")) {
                Toast.makeText(
                                MainActivity.this,
                                "Error while adding new book! No blank field!",
                                Toast.LENGTH_SHORT)
                        .show();
            } else {
                Book newBook = null;
                try {
                    newBook = new Book(eTitle.getText().toString(), eISBN.getText().toString(), eAuthor.getText().toString(), Integer.parseInt(sQuantity.getSelectedItem().toString()), Integer.parseInt(eNOP.getText().toString()), sGenre.getSelectedItem().toString(), sLocation.getSelectedItem().toString(), new SimpleDateFormat("yyyy").parse(sPublishingYear.getSelectedItem().toString()) , ePublisher.getText().toString(), new ArrayList<BorrowingProcess>());
                    eTitle.setText("");
                    eISBN.setText("");
                    eAuthor.setText("");
                    ePublisher.setText("");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DataHandling.bookList.add(newBook);
                Toast.makeText(
                                MainActivity.this,
                                "New book added successfully!",
                                Toast.LENGTH_SHORT)
                        .show();
            }

            dialog.dismiss();
        });

        bCancel.setOnClickListener(view1 -> dialog.dismiss());
        builder.setView(viewAddDialog);
        dialog = builder.create();

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