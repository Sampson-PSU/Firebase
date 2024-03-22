package com.example.firebaseauthentication.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class ListBooksActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private BooksAdapter mBooksAdapter;
    ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_books);

        mRecyclerView = findViewById(R.id.recyclerView);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("books");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<>();
        mRecyclerView.setAdapter(mBooksAdapter);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Book book = dataSnapshot.getValue(Book.class);
                        bookList.add(book);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                mBooksAdapter.notifyAll();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


