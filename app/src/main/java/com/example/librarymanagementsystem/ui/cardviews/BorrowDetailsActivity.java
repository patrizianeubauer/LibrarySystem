package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class BorrowDetailsActivity extends AppCompatActivity  {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private ArrayList<User> userList;
    private Spinner spinner;
    private AlertDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_with_user_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.helperList = new ArrayList<>();
        setBookInfo();
        userList = DataHandling.getUserList();

        String[] options = new String[userList.size()];
        int i = 0;
        for(User u: userList) {
            options[i] = u.getVorname()+" "+u.getNachname();
            i++;
        }

        this.spinner = findViewById(R.id.spinnerBorrow);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,options);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (getIntent().hasExtra("some_book")) {
            Book book = (Book) getIntent().getSerializableExtra("some_book");

        }

        Button addNewUserButton = findViewById(R.id.addNewUserButton);
        addNewUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                Snackbar.make(view, "Adding new user", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new user:");
        View viewAddDialog = getLayoutInflater().inflate(R.layout.add_new_user_dialog, null);
        EditText eVorname = viewAddDialog.findViewById(R.id.vorname);
        EditText eNachname = viewAddDialog.findViewById(R.id.nachname);
        Button bAdd, bCancel;
        bAdd = viewAddDialog.findViewById(R.id.addButton);
        bCancel = viewAddDialog.findViewById(R.id.cancelButton);

        bAdd.setOnClickListener(view -> {
            String vorname = eVorname.getText().toString();
            String nachname = eNachname.getText().toString();

            if (vorname.equals("")) {
                Toast.makeText(
                                BorrowDetailsActivity.this,
                                "Error while adding new user! No blank field!",
                                Toast.LENGTH_SHORT)
                        .show();
            } else {
                //Book newBook = new Book(eTitle.getText().toString(), eISBN.getText().toString(), eAuthor.getText().toString(), Integer.parseInt(sQuantity.getSelectedItem().toString()), Integer.parseInt(eNOP.getText().toString()), sGenre.getSelectedItem().toString(), sLocation.getSelectedItem().toString(), new Date(), ePublisher.getText().toString(), new ArrayList<BorrowingProcess>());
                User newUser = new User("","","");
                DataHandling.userList.add(newUser);
                Toast.makeText(
                                BorrowDetailsActivity.this,
                                "New user added successfully!",
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

    private void setBookInfo() {
        bookList = DataHandling.getBookList();

        for(Book b:bookList) {
            if(b.isAvailable()) {
                helperList.add(b);
            }
        }
    }

}
