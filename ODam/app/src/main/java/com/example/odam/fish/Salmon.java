package com.example.odam.fish;

import com.example.odam.R;

public class Salmon extends Fish {
    public Salmon() {
        super();
        fishType = "Salmon";
        imageID = R.drawable.fish_salmon;
        health = 100;
        baseSpeed = 5;
        speed = baseSpeed;
    }
}
