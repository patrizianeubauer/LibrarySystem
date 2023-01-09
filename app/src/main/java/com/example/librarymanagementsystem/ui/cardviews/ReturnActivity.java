package com.example.librarymanagementsystem.ui.cardviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

public class ReturnActivity extends AppCompatActivity implements recyclerAdapterReturns.DetailsListener {

    private ArrayList<Book> helperList;
    private ArrayList<Book> bookList;
    private RecyclerView recyclerView;
    private recyclerAdapterReturns adapter;
    private User user;
    private AlertDialog dialog;
    private TextView tFees;
    private View viewFeesDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.return_items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        helperList = new ArrayList<>();
        user = DataHandling.getCurrentUser();
        setBookInfo();
        setAdapter();
        bookList = DataHandling.bookList;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Fees");
        viewFeesDialog = getLayoutInflater().inflate(R.layout.fees_dialog, null);
        tFees = viewFeesDialog.findViewById(R.id.tvFees);
        Button bAdd, bCancel;
        bAdd = viewFeesDialog.findViewById(R.id.addButton);
        bCancel = viewFeesDialog.findViewById(R.id.cancelButton);

        bAdd.setOnClickListener(view -> {
            Toast.makeText(
                            ReturnActivity.this,
                            "Fees paid!",
                            Toast.LENGTH_SHORT)
                    .show();
            dialog.dismiss();

            Toast.makeText(ReturnActivity.this, "Returning was successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(ReturnActivity.this, MainActivity.class);
            startActivity(intent);
            adapter.notifyDataSetChanged();
        });

        bCancel.setOnClickListener(view1 -> dialog.dismiss());
        builder.setView(viewFeesDialog);
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

    private void setAdapter() {
        adapter = new recyclerAdapterReturns(helperList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setBookInfo() {
        for(Book b: DataHandling.bookList) {
            for(BorrowingProcess bp: b.getBorrowers()) {
                if(bp.getUser() != null && user != null && bp.getUser().getId() == user.getId()) {
                    helperList.add(b);
                }
            }
        }
    }

    @Override
    public void onDetailClickExtend(int position) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(helperList.get(position).getTitle())) {
                for (BorrowingProcess bp : helperList.get(position).getBorrowers()) {
                    if (bp.getUser().getId() == user.getId()) {
                        System.out.println(bp.getExtensionCounter());
                        if (bp.getExtensionCounter() > 1) {
                            Toast.makeText(ReturnActivity.this, "Extension not possible!", Toast.LENGTH_SHORT).show();
                        } else {
                            bookList.get(i).incrementCounterinABP(bp);
                            bookList.get(i).updateReturnDate(bp);
                            Toast.makeText(ReturnActivity.this, "Extension successful!", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }

        Intent intent = new Intent(ReturnActivity.this, MainActivity.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetailClickReturn(int position) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().equals(helperList.get(position).getTitle())) {
                for (BorrowingProcess bp : helperList.get(position).getBorrowers()) {
                    if (bp.getUser().getId() == user.getId()) {
                        bookList.get(i).removeABP(bp);
                        if (bp.getFees() > 0) {
                            tFees.setText(bp.getFees()+" €");
                            dialog.show();
                            if (bookList.get(i).getBorrowers().size() < bookList.get(i).getNumberAvailable()) {
                                bookList.get(i).setAvailable(true);
                            }
                        } else {
                            if (bookList.get(i).getBorrowers().size() < bookList.get(i).getNumberAvailable()) {
                                bookList.get(i).setAvailable(true);
                            }
                            Toast.makeText(ReturnActivity.this, "Returning was successful!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ReturnActivity.this, MainActivity.class);
                            startActivity(intent);
                            adapter.notifyDataSetChanged();
                        }
                        break;
                    }
                }
            }
        }
    }
}
