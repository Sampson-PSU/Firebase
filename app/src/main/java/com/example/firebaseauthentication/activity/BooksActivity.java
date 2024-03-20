package com.example.firebaseauthentication.activity;

import android.os.Bundle;
import android.util.Log;

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
import java.util.List;

public class BooksActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private BooksAdapter mBooksAdapter;
    private ValueEventListener valueEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_list_books);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBooksAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("books");
        mDatabaseReference.addListenerForSingleValueEvent(valueEventListener);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Book> bookList = new ArrayList<>();
                bookList.clear();
                if(snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Book book = snapshot.getValue(Book.class);
                        bookList.add(book);
                    }
                    mBooksAdapter = new BooksAdapter(bookList);
                    mRecyclerView.setAdapter(mBooksAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.e("BookListActivity", "Error retrieving books from database",
                        error.toException());
            }
        };
    }
}
