package com.example.odam.fish;

import com.example.odam.R;

public class Swordfish extends Fish {
    public Swordfish() {
        super();
        fishType = "Swordfish";
        imageID = R.drawable.swordfish;
        health = 100;
        baseSpeed = 12;
        speed = baseSpeed;
    }
}
