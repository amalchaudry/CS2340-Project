package com.example.odam.fish;

import com.example.odam.R;

public class Shark extends Fish {
    public Shark() {
        super();
        fishType = "Shark";
        imageID = R.drawable.fish_shark;
        health = 200;
        baseSpeed = 12;
        speed = baseSpeed;
    }
}
