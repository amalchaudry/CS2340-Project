package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.odam.databinding.ActivityWinBinding;
import com.example.odam.gameLogic.Game;

public class WinActivity extends AppCompatActivity {
    private ActivityWinBinding binding;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        binding = ActivityWinBinding.inflate(getLayoutInflater());
        Button button = findViewById(R.id.buttonreStart);
        View view = binding.getRoot();
        setContentView(view);
        binding.totalDeathText.setText("Total Deaths: " + game.getDeathCounter());
        binding.fishKilledText.setText("Fish Killed: " + game.getFishKilled());
        binding.moneySpentText.setText("Money Spent: " + game.moneySpent());
        binding.buttonreStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchActivitiesWin();
            }
        });
    }
    private void switchActivitiesWin() {
        Intent switchActivityIntent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(switchActivityIntent);
    }
}
