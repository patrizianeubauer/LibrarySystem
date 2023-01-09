package com.example.librarymanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.data.DataHandling;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.BorrowingProcess;
import com.example.librarymanagementsystem.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class recyclerAdapterReturns extends RecyclerView.Adapter<recyclerAdapterReturns.MyViewHolder> {

    private ArrayList<Book> bookList;
    private recyclerAdapterReturns.DetailsListener onDetailsListener;
    private User user;

    public recyclerAdapterReturns(ArrayList<Book> bookList, recyclerAdapterReturns.DetailsListener onDetailsListener) {
        this.bookList = bookList;
        this.onDetailsListener = onDetailsListener;
        this.user = DataHandling.getCurrentUser();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameText;
        private recyclerAdapterReturns.DetailsListener detailsListener;
        private Button buttonReturn;
        private Button buttonExtend;

        public MyViewHolder(final View view, recyclerAdapterReturns.DetailsListener detailsListener) {
            super(view);
            nameText = view.findViewById(R.id.textView);
            this.detailsListener = detailsListener;
            buttonReturn = view.findViewById(R.id.buttonReturn);
            buttonExtend = view.findViewById(R.id.buttonExtend);
            buttonReturn.setOnClickListener(this);
            buttonExtend.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.buttonExtend:
                    this.detailsListener.onDetailClickExtend(getAdapterPosition());
                    break;
                case R.id.buttonReturn:
                    this.detailsListener.onDetailClickReturn(getAdapterPosition());
                    break;
            }
        }
    }

    @NonNull
    @Override
    public recyclerAdapterReturns.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.return_item, parent, false);
        return new recyclerAdapterReturns.MyViewHolder(itemView, onDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterReturns.MyViewHolder holder, int position) {
        float fees = 0.0f;
        String title = bookList.get(position).getTitle();


        ArrayList<Book> books = DataHandling.getBookList();
        for (Book book : books) {

            for (BorrowingProcess bp : book.getBorrowers()) {
                if (book.getTitle().equals(bookList.get(position).getTitle())) {
                    if (bp.getExtensionCounter() > 1 || bp.getFees() > 0.0f) {
                        holder.buttonExtend.setEnabled(false);
                    }
                    if(bp.getUser().getId() == user.getId()) {
                        fees = bp.getFees();
                    }
                }
            }
        }

        String newName="";
        if(title.length() > 20) {
            newName = title.substring(0, 20)+".. ";
        } else {
            newName = title;
        }

        holder.nameText.setText(newName+" (+"+fees+"€)");
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface DetailsListener {
        void onDetailClickExtend(int position);
        void onDetailClickReturn(int position);
    }

    public void setFilteredList(ArrayList<Book> filteredList) {
        this.bookList = filteredList;
        notifyDataSetChanged();
    }

}
