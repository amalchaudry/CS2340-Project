package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.odam.databinding.ActivityConfigBinding;
import com.example.odam.databinding.ActivityWelcomeScreenBinding;

public class ConfigActivity extends AppCompatActivity {

    private ActivityConfigBinding binding;
    private String lightTeal = "#FF03DAC5";
    private String darkTeal = "#FF018786";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.easyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GameApplication) getApplication()).setDiff(Difficulty.EASY);
                binding.easyButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(lightTeal)));
                binding.mediumButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
                binding.hardButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
            }
        });

        binding.mediumButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GameApplication) getApplication()).setDiff(Difficulty.MEDIUM);
                binding.easyButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
                binding.mediumButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(lightTeal)));
                binding.hardButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
            }
        });

        binding.hardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((GameApplication) getApplication()).setDiff(Difficulty.HARD);
                binding.easyButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
                binding.mediumButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(darkTeal)));
                binding.hardButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(lightTeal)));
            }
        });

        binding.continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (binding.nameTextEdit.getText().toString().trim().length() > 0) {
                    ((GameApplication) getApplication()).setName(binding.nameTextEdit.getText().toString());
                    switchActivities();
                }
            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, FirstMapActivity.class);
        startActivity(switchActivityIntent);
    }
}