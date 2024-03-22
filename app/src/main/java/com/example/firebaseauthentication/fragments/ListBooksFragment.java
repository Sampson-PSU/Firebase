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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListBooksFragment extends Fragment {
    private BooksAdapter booksAdapter;
    private RecyclerView booksRecyclerView;
    private DatabaseReference mDatabaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.fragment_list_books, container, false);

        booksRecyclerView = view.findViewById(R.id.recyclerView);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize Firebase Realtime Database reference.
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("books");

        // Retrieve data from Firebase.
        // Fetching all books.
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Book> bookList = new ArrayList<>();
                try {
                    if (dataSnapshot.exists())
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Book book = snapshot.getValue(Book.class);
                            bookList.add(book);
                        }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                booksAdapter = new BooksAdapter(bookList);
                booksRecyclerView.setAdapter(booksAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error.

            }
        });
        return view;
    }
}


    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Initialize Firebase Realtime Database Reference.
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("books");
    }*/