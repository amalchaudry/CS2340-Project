package com.example.odam.tower;

import android.util.Log;

import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;

public class BoatTower extends Tower {
    private double slowPercent = 0.6;
    public BoatTower(Difficulty diff) {
        name = "Boatman";
        imageID = R.drawable.boat1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        switch (diff) {
        case MEDIUM:
            cost = 650;
            break;
        case HARD:
            cost = 750;
            break;
        default:
            cost = 550;
            break;
        }
        range = 300;
        cooldown = 0;
    }

    public void update(ArrayList<Fish> fishes) {
        for (int i = 0; i < fishes.size(); i++) {
            Fish fish = fishes.get(i);
            if (i == 0) {
                Log.d("diff", fish.getX() + "-" + x);
            }
            int diffX = fish.getX() - x;
            int diffY = fish.getY() - y;
            double distanceToTower =  Math.sqrt(diffX * diffX + diffY * diffY);
            attack(fish, distanceToTower);
        }
    }

    public void attack(Fish fish, double distance) {
        if (!fish.isSlowed() && distance <= range) {
            fish.setSpeed((int) (slowPercent * fish.getSpeed()));
            fish.setSlowed(true);
        } else if (fish.isSlowed() && distance > range) {
            fish.setSlowed(false);
            fish.setSpeed((int) (fish.getSpeed() / slowPercent));
        }
    }
}
