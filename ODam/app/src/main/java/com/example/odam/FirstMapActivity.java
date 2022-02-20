package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

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


        Difficulty diff = ((GameApplication) getApplication()).getDiff();
        Log.d("Diff", diff.toString());
        int money;
        int lakeHP;
        switch (diff) {
        case MEDIUM:
            money = 500;
            lakeHP = 150;
            break;
        case HARD:
            money = 400;
            lakeHP = 100;
            break;
        default:
            money = 600;
            lakeHP = 200;
            break;
        }

        binding.moneyText.setText("Money: " + money);
        binding.lakeHealthText.setText("Lake HP: " + lakeHP);
    }
}