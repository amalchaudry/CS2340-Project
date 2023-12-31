package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;


import android.widget.Button;
import android.os.Bundle;

import com.example.odam.databinding.ActivityWelcomeScreenBinding;

public class WelcomeScreenActivity extends AppCompatActivity {

    private ActivityWelcomeScreenBinding binding;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_welcome_screen);

        binding = ActivityWelcomeScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonQuit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchActivities();
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, ConfigActivity.class);
        startActivity(switchActivityIntent);
    }
}