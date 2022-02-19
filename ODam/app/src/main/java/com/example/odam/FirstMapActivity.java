package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.odam.databinding.ActivityFirstMapBinding;
import com.example.odam.databinding.ActivityMainBinding;

public class FirstMapActivity extends AppCompatActivity {

    private ActivityFirstMapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstMapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int lakeHP = 0; //change this to be equivalent to other configuration screen's difficulty variable
        binding.lakeHealthText.setText("Lake HP: " + lakeHP);
    }
}