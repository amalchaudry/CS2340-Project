package com.example.odam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.odam.databinding.ActivityFirstMapBinding;

public class FirstMapActivity extends AppCompatActivity {

    private  ActivityFirstMapBinding binding;
    private Tower chosenTower;
    private ImageView chosenTowerImage;
    private Difficulty diff;
    private boolean isPlacingTower = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFirstMapBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //change this to be equivalent to other configuration screen's difficulty variable
        chosenTowerImage = new ImageView(FirstMapActivity.this);

        diff = ((GameApplication) getApplication()).getDiff();
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

        //testing
        binding.fisherButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chosenTower = new FishermanTower(diff);
                chosenTowerImage.setImageResource(chosenTower.getImage()); //TODO: Change to appropriate image
                isPlacingTower = true;
            }
        });

        //TODO: Try drag and drop listener instead
        binding.mapImage.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("Hello");
                chosenTowerImage.bringToFront();
                int action = event.getAction();
                switch (action) {
                case MotionEvent.ACTION_DOWN:
                    chosenTowerImage.setX(event.getX());
                    chosenTowerImage.setY(event.getY());
                case MotionEvent.ACTION_MOVE:
                    chosenTowerImage.setX(event.getX());
                    System.out.println(chosenTowerImage.getX());
                    chosenTowerImage.setY(event.getY());
                case MotionEvent.ACTION_UP:
                    chosenTowerImage.setX(event.getX());
                    chosenTowerImage.setY(event.getY());
                }
                return true;
            }
        });
    }
}