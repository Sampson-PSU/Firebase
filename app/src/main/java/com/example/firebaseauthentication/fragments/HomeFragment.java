package com.example.firebaseauthentication.fragments;

// Import all necessary libraries.
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebaseauthentication.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Get reference to TextView views for current login user.
        TextView userName = rootView.findViewById(R.id.identifier_text);
        TextView signInDate = rootView.findViewById(R.id.sign_in_date_text);
        TextView signInTime = rootView.findViewById(R.id.sign_in_time_text);

        // Retrieve the current user email address from Firebase Authentication.
        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // Get current date and time in the specified formats.
        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("K:mm a, z", Locale.getDefault()).format(new Date());

        // Set values to TextViews.
        userName.setText(currentUser);
        signInDate.setText(currentDate);
        signInTime.setText(currentTime);

        return rootView; // Return the root view for this fragment.
    }
}
