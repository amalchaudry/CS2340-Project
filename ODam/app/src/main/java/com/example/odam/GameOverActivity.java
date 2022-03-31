package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.odam.databinding.ActivityGameOverBinding;
import com.example.odam.databinding.ActivityWelcomeScreenBinding;

public class GameOverActivity extends AppCompatActivity {
    private ActivityGameOverBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        binding = ActivityGameOverBinding.inflate(getLayoutInflater());
        Button button = findViewById(R.id.buttonreStart);
        binding.buttonreStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivities();
            }
        });
    }
    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(switchActivityIntent);
    }
}