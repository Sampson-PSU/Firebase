package com.example.firebaseauthentication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthentication.R;
import com.example.firebaseauthentication.adapter.BooksAdapter;
import com.example.firebaseauthentication.model.Book;

import java.util.ArrayList;
import java.util.List;

public class ListBooksFragment extends Fragment {
    private List<Book> mBooks;
    private RecyclerView booksRecyclerView;
    private BooksAdapter booksAdapter;

    public ListBooksFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_list_books, container, false);

        mBooks = generateBookItems();

        booksRecyclerView = rootView.findViewById(R.id.recyclerView);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        booksAdapter = new BooksAdapter(mBooks);
        ArrayList<Book> booksList = new ArrayList<>();
        booksRecyclerView.setAdapter(booksAdapter);

        return rootView;
    }
    private List<Book> generateBookItems() {
        List<Book> mBooks = new ArrayList<>();
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        mBooks.add(new Book("title", "author", "publisher", "publication_date", "isbn"));
        return mBooks;
        //return rootView;
    }
}
