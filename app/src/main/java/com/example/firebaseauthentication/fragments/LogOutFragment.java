package com.example.firebaseauthentication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebaseauthentication.R;
import com.example.firebaseauthentication.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutFragment extends Fragment implements View.OnClickListener {

    private Button btnLogOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View rootView = inflater.inflate(R.layout.fragment_log_out, container, false);

        // Get reference to Button views.
        btnLogOut = rootView.findViewById(R.id.btn_log_out);
        btnLogOut.setOnClickListener(this);

        return rootView;
    }
    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.btn_log_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
    }
}
