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
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

public class BorrowDetailsActivity extends AppCompatActivity {

    private ArrayList<Book> bookList;
    private ArrayList<Book> helperList;
    private ArrayList<User> userList;
    private Button addNewUserButton, addNewUser, registerButton;
    private Spinner spinner;
    private AlertDialog dialog, dialogInfo;
    private User newUser = DataHandling.getCurrentUser();
    private Book book;
    private String item;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_with_user_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helperList = new ArrayList<>();
        bookList = new ArrayList<>();
        userList = new ArrayList<>();
        setBookInfo();
        userList.addAll(DataHandling.getUserList());
        ArrayList<BorrowingProcess> bpList;

        book = (Book) getIntent().getSerializableExtra("some_book");
        bpList = book.getBorrowers();
        String[] options = new String[userList.size() - bpList.size()];

        int i = 0;
        for (User u : userList) {
            if (!book.BPcontainsUser(u)) {
                options[i] = u.getVorname() + " " + u.getNachname() + " (ID: " + u.getId() + ")";
                i++;
            }
        }

        this.spinner = findViewById(R.id.spinnerBorrow);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, options);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter new user:");
        View viewAddDialog = getLayoutInflater().inflate(R.layout.register_dialog, null);
        Button bAdd, bCancel;
        bAdd = viewAddDialog.findViewById(R.id.addButton);
        bCancel = viewAddDialog.findViewById(R.id.cancelButton);

        bAdd.setOnClickListener(view2 -> {
            EditText eUsername = viewAddDialog.findViewById(R.id.username);
            String username = eUsername.getText().toString();
            EditText eNachname = viewAddDialog.findViewById(R.id.nachname);
            String nachname = eNachname.getText().toString();
            EditText eVorname = viewAddDialog.findViewById(R.id.vorname);
            String vorname = eVorname.getText().toString();
            EditText eEmail = viewAddDialog.findViewById(R.id.email);
            String email = eEmail.getText().toString();
            EditText eStreet = viewAddDialog.findViewById(R.id.street);
            String street = eStreet.getText().toString();
            EditText eZipCode = viewAddDialog.findViewById(R.id.zipcode);
            String zipcode = eZipCode.getText().toString();
            EditText eCity = viewAddDialog.findViewById(R.id.city);
            String city = eCity.getText().toString();
            EditText eFirstPassword = viewAddDialog.findViewById(R.id.firstPassword);
            String firstPassword = eFirstPassword.getText().toString();
            EditText eSecondPassword = viewAddDialog.findViewById(R.id.secondPassword);
            String secondPassword = eSecondPassword.getText().toString();

            ArrayList<User> newUserList = new ArrayList<>();
            newUserList.addAll(DataHandling.getUserList());

            for (User user : newUserList) {
                if (user.getUsername().equals(username)) {
                    Toast.makeText(BorrowDetailsActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
            }

            if (username.equals("") || nachname.equals("") || vorname.equals("") || zipcode.equals("") || email.equals("")
                    || street.equals("") || city.equals("") || !firstPassword.equals(secondPassword)) {
                if (!firstPassword.equals(secondPassword)) {
                    Toast.makeText(BorrowDetailsActivity.this, "Retype password correct! Try again!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BorrowDetailsActivity.this, "No empty fields! Try again!", Toast.LENGTH_SHORT).show();
                }
            } else {
                User newUser = new User(username, vorname, nachname, email, street, zipcode, city, firstPassword);
                DataHandling.addNewUser(newUser);
                Toast.makeText(BorrowDetailsActivity.this, "New account created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BorrowDetailsActivity.this, BorrowDetailsActivity.class);
                intent.putExtra("some_book", book);
                startActivity(intent);
            }

            dialog.dismiss();

        });

        bCancel.setOnClickListener(view2 -> dialog.dismiss());
        builder.setView(viewAddDialog);
        dialog = builder.create();

        // viewAddDialog
        addNewUser = findViewById(R.id.addNewUserButton);
        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        // viewInfoDialog
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Borrow information:");
        View viewInfoDialog = getLayoutInflater().inflate(R.layout.borrow_info_dialog, null);
        TextView tvTitle = viewInfoDialog.findViewById(R.id.title);
        TextView tvReturnDate = viewInfoDialog.findViewById(R.id.returnDate);
        TextView tvBorrower = viewInfoDialog.findViewById(R.id.borrower);

        tvTitle.setText(book.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        tvReturnDate.setText(sdf.format(cal.getTime()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                item = parent.getItemAtPosition(position).toString();
                tvBorrower.setText(item);
                DataHandling.setCurrentUser(DataHandling.getUser(item));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button bOk = viewInfoDialog.findViewById(R.id.okButton);
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInfo.dismiss();

                DataHandling.addBP(book, DataHandling.getCurrentUser());
                Intent intent = new Intent(BorrowDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        builder2.setView(viewInfoDialog);
        dialogInfo = builder2.create();

        Button bBorrow = findViewById(R.id.borrowButton);
        bBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogInfo.show();
            }
        });
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
        bookList.addAll(DataHandling.getBookList());

        for (Book b : bookList) {
            if (b.isAvailable()) {
                helperList.add(b);
            }
        }
    }

}
