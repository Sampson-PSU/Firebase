package com.example.firebaseauthentication.activity;

// Import all necessary libraries.
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use a Handler object to delay the launch of the LoginActivity by 5 seconds.
        // After 5 seconds, an intent is created to launch the LoginActivity, and the finish()
        // method is called to destroy the splash screen activity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after a delay.
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent); // Launch LoginActivity.
                finish(); // Close the current splash screen activity.
            }
        }, 5000); // Delay in milliseconds (5 seconds).
    }
}