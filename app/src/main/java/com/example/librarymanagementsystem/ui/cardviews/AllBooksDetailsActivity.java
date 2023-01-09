package com.example.librarymanagementsystem.ui.cardviews;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.model.Book;

import java.text.SimpleDateFormat;

public class AllBooksDetailsActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvISBN;
    private TextView tvAuthor;
    private TextView tvAvailable;
    private TextView tvNumberOfPages;
    private TextView tvGenre;
    private TextView tvLocation;
    private TextView tvPublishingYear;
    private TextView tvPublisher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("some_detail")) {
            Book book = (Book) getIntent().getSerializableExtra("some_detail");
            tvTitle = findViewById(R.id.tvTitle);
            tvTitle.setText(book.getTitle());
            tvISBN = findViewById(R.id.tvISBN);
            tvISBN.setText(book.getIsbn());
            tvAuthor = findViewById(R.id.tvAuthor);
            tvAuthor.setText(book.getAuthor());
            tvNumberOfPages = findViewById(R.id.tvNumberOfPages);
            tvNumberOfPages.setText(book.getNumberOfPages()+"");
            tvGenre = findViewById(R.id.tvGenre);
            tvGenre.setText(book.getGenre());
            tvLocation = findViewById(R.id.tvLocation);
            tvLocation.setText(book.getLocation());
            tvPublishingYear = findViewById(R.id.tvPublishingYear);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
            tvPublishingYear.setText(sdf1.format(book.getpublishingYear()));
            tvPublisher = findViewById(R.id.tvPublisher);
            tvPublisher.setText(book.getPublisher());
            tvAvailable = findViewById(R.id.tvAvailable);
            if (book.isAvailable()) {
                tvAvailable.setText("Yes");
                tvAvailable.setTextColor(Color.parseColor("#32CD32"));
            } else {
                tvAvailable.setText("No");
                tvAvailable.setTextColor(Color.parseColor("#C70039"));
            }
        }
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
}
