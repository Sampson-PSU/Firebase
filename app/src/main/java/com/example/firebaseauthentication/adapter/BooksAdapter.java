package com.example.firebaseauthentication.adapter;

// Import all necessary libraries.
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthentication.R;
import com.example.firebaseauthentication.model.Book;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    // Declare a private list to hold books.
    private final List<Book> bookList;

    // Initialize adapter with a list of books.
    public BooksAdapter(List<Book> bookList) {

        this.bookList = bookList;
    }

    // Create a new ViewHolder for each item in the list.
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    // Bind data to the views in the ViewHolder.
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        // Set the book details to the views in the ViewHolder.
        holder.mTitleTextView.setText(bookList.get(position).getTitle());
        holder.mAuthorTextView.setText(bookList.get(position).getAuthor());
        holder.mPublisherTextView.setText(bookList.get(position).getPublisher());
        holder.mPublicationDateTextView.setText(bookList.get(position).getPublication());
        holder.mIsbnTextView.setText(bookList.get(position).getIsbn());
    }

    // Return the total number of items in the list.
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // Static class representing the ViewHolder for a single book item.
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView mTitleTextView;
        TextView mAuthorTextView;
        TextView mPublisherTextView;
        TextView mPublicationDateTextView;
        TextView mIsbnTextView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the TextViews.
            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            mAuthorTextView = itemView.findViewById(R.id.author_text_view);
            mPublisherTextView = itemView.findViewById(R.id.publisher_text_view);
            mPublicationDateTextView = itemView.findViewById(R.id.publication_date_text_view);
            mIsbnTextView = itemView.findViewById(R.id.isbn_text_view);
        }
    }
}