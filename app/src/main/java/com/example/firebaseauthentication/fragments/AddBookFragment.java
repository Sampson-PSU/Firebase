package com.example.firebaseauthentication.fragments;

// Import all necessary libraries.
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddBookFragment extends Fragment implements View.OnClickListener {

    // Declare UI elements.
    private EditText titleEditText, authorEditText, publisherEditText, publicationEditText, isbnEditText;
    //private Button btnAddBook;

    // Create a reference to Firebase Realtime Database.
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("book");

    // Generate a unique key for the new child (book).
    String bookKey = databaseReference.push().getKey();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_add_book, container, false);

        // Initialize UI elements by finding their views in the inflated layout.
        titleEditText = rootView.findViewById(add_book_title);
        authorEditText = rootView.findViewById(R.id.add_book_author);
        publisherEditText = rootView.findViewById(R.id.add_book_publisher);
        publicationEditText = rootView.findViewById(R.id.add_book_publication);
        isbnEditText = rootView.findViewById(R.id.add_book_isbn);
        Button btnAddBook = rootView.findViewById(R.id.btn_add_book);
        Button btnDeleteBook = rootView.findViewById(R.id.btn_delete_book);

        // Set click listener for buttons.
        btnAddBook.setOnClickListener(this);
        btnDeleteBook.setOnClickListener(this);

        return rootView; // Return the root view for this fragment.
    }

    @Override
    public void onClick(View view) {

        // Handle button click based on id.
        int id = view.getId();
        if (id == R.id.btn_add_book) {
            insertBook(); // Call the method to insert a book.
        }
        if (id == R.id.btn_delete_book) {
            deleteBook(); // Call the method to delete a book.
        }
    }

    public void deleteBook() {

        // Get the ISBN from the EditText input.
        String isbn = isbnEditText.getText().toString();

        // Get reference to ("books") node in the Firebase Realtime Database.
        databaseReference = FirebaseDatabase.getInstance().getReference("books");

        // Create a query to find the book with the specified ISBN.
        Query query = FirebaseDatabase.getInstance().getReference("books")
                .orderByChild("isbn")
                .equalTo(isbn);

        // Add a listener to retrieve data for the specified query.
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Iterate through the result to find the book with matching ISBN.
                for (DataSnapshot isbnSnapshot : snapshot.getChildren()) {
                    // Remove the book from the database.
                    isbnSnapshot.getRef().removeValue();
                    // Display success message for delete.
                    Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors or cancellations.
            }
        });
    }

        public void insertBook () {

        // Get book details from EditText inputs.
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String publisher = publisherEditText.getText().toString();
        String publication = publicationEditText.getText().toString();
        String isbn = isbnEditText.getText().toString();

        // Retrieve details to create a Book object.
        Book book = new Book(title, author, publisher, publication, isbn);

        // Check is bookKey is not null or previously generated.
        assert bookKey != null;

        // Save the book to the "books" node in the database.
        FirebaseDatabase.getInstance().getReference("books").child(bookKey)
                .setValue(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Display a success message.
                            Toast.makeText(getContext(), "Book Saved",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any failure.
                        // Failure message.
                        Toast.makeText(getContext(), e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        }
}