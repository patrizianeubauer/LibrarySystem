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
import android.widget.Toast;

import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allBooks = findViewById(R.id.allBooks);
        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "List of all books!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        searchBooks = findViewById(R.id.search);
        searchBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Search for books!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        returnBooks = findViewById(R.id.returns);
        returnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Return a book!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ReturnActivity.class);
                startActivity(intent);
            }
        });

        borrowingBooks = findViewById(R.id.borrow);
        borrowingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Borrowing a book!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BorrowActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Snackbar.make(view, "Adding new book", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                Book newBook = new Book(eTitle.getText().toString(), eISBN.getText().toString(), eAuthor.getText().toString(), Integer.parseInt(sQuantity.getSelectedItem().toString()), Integer.parseInt(eNOP.getText().toString()), sGenre.getSelectedItem().toString(), sLocation.getSelectedItem().toString(), new Date(), ePublisher.getText().toString(), new ArrayList<BorrowingProcess>());
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
}