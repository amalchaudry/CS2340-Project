package com.example.odam.tower;

import android.util.Log;

import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;

public class BoatTower extends Tower {
    private double slowPercent = 0.6;
    private ArrayList<Fish> fishInRange = new ArrayList<>();
    public BoatTower(Difficulty diff) {
        name = "Boatman";
        imageID = R.drawable.boat1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        switch (diff) {
        case MEDIUM:
            cost = 950;
            break;
        case HARD:
            cost = 1050;
            break;
        default:
            cost = 850;
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

    /**
     * If fish is new to range, then slow
     * If fish has been in range, then nothing happens
     * If fish, leaves range, then speed it up again and remove from array
     * @param fish  the fish in question
     * @param distance  distance from tower to fish
     */
    public void attack(Fish fish, double distance) {
        if (!fishInRange.contains(fish) && distance <= range) {
            fish.setSlowed(true);
            fishInRange.add(fish);
            fish.setSpeed((int) (slowPercent * fish.getSpeed()));
        }
        if (fishInRange.contains(fish) && distance > range) {
            fish.setSlowed(false);
            fishInRange.remove(fish);
            fish.setSpeed((int) (fish.getSpeed() / slowPercent));
        }
    }

}
