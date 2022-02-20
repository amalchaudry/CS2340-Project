package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;


import android.graphics.Color;
import android.widget.Button;
import android.os.Bundle;

import com.example.odam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}