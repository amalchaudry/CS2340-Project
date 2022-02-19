package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.odam.databinding.ActivityFirstMapBinding;

public class FirstMapActivity extends AppCompatActivity {

    private  ActivityFirstMapBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstMapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

         //change this to be equivalent to other configuration screen's difficulty variable


        Difficulty diff = Difficulty.EASY;
        int money;
        int lakeHP;
        switch (diff) {
            case EASY:
                money = 600;
                lakeHP = 200;
            case MEDIUM:
                money = 500;
                lakeHP = 150;
            case HARD:
                money = 400;
                lakeHP = 100;
            default:
                money = 600;
                lakeHP = 200;
        }

        binding.moneyText.setText("Money: " + money);
        binding.lakeHealthText.setText("Lake HP: " + lakeHP);
    }
}