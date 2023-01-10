package com.example.librarymanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.model.Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class recyclerAdapterAllBooks extends RecyclerView.Adapter<recyclerAdapterAllBooks.MyViewHolder> {

    private ArrayList<Book> bookList;
    private DetailsListener onDetailsListener;

    public recyclerAdapterAllBooks(ArrayList<Book> bookList, DetailsListener onDetailsListener) {
        this.bookList = bookList;
        this.onDetailsListener = onDetailsListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleText;
        private DetailsListener detailsListener;
        private Button button;

        public MyViewHolder(final View view, DetailsListener detailsListener) {
            super(view);
            titleText = view.findViewById(R.id.textView);
            this.detailsListener = detailsListener;
            button = view.findViewById(R.id.buttonDetails);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.detailsListener.onDetailClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public recyclerAdapterAllBooks.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView, onDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterAllBooks.MyViewHolder holder, int position) {
        String name = bookList.get(position).getTitle();
        String newName="";
        if(name.length() > 60) {
            newName = name.substring(0, 60)+" ... ";
        } else {
            newName = name;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        holder.titleText.setText(newName+" ("+sdf.format(bookList.get(position).getpublishingYear())+")");
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface DetailsListener {
        void onDetailClick(int position);
    }

    public void setFilteredList(ArrayList<Book> filteredList) {
        this.bookList = filteredList;
        notifyDataSetChanged();
    }
}
