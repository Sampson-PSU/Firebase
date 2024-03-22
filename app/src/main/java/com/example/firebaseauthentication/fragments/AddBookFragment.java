package com.example.firebaseauthentication.fragments;

import static com.example.firebaseauthentication.R.id.add_book_title;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebaseauthentication.R;
import com.example.firebaseauthentication.model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBookFragment extends Fragment implements View.OnClickListener {
    private EditText titleEditText, authorEditText, publisherEditText, publicationEditText, isbnEditText;
    private Button btnAddBook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_add_book, container, false);

        titleEditText = rootView.findViewById(add_book_title);
        authorEditText = rootView.findViewById(R.id.add_book_author);
        publisherEditText = rootView.findViewById(R.id.add_book_publisher);
        publicationEditText = rootView.findViewById(R.id.add_book_publication);
        isbnEditText = rootView.findViewById(R.id.add_book_isbn);
        Button btnAddBook = rootView.findViewById(R.id.btn_add_book);

        btnAddBook.setOnClickListener(this);

        //startActivity(new Intent(getContext(), AddBookActivity.class));
        return rootView;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btn_add_book) {
            insertBook();
        }
    }

    public void insertBook() {

        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String publisher = publisherEditText.getText().toString();
        String publication = publicationEditText.getText().toString();
        String isbn = isbnEditText.getText().toString();

        Book book = new Book(title, author, publisher, publication, isbn);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("book");
        // Generate a unique key for the new child (book).
        String bookKey = databaseReference.push().getKey();

        assert bookKey != null;
        FirebaseDatabase.getInstance().getReference("books").child(bookKey)
                .setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Book Saved",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}