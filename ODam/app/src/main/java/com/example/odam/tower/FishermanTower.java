package com.example.odam.tower;

import android.util.Log;

import com.example.odam.FirstMapActivity;
import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;

public class FishermanTower extends Tower {
    private int cooldownFrames = (int) (1 * FirstMapActivity.fps);
    private int fishCount = 0;
    private int counter = 0;
    private Difficulty currentDiff;
    public FishermanTower(Difficulty diff) {
        name = "Fisherman";
        imageID = R.drawable.fisherman1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        currentDiff = diff;
        switch (diff) {
        case MEDIUM:
            cost = 450;
            break;
        case HARD:
            cost = 550;
            break;
        default:
            cost = 350;
            break;
        }
        range = 500;
        cooldown = 1;
    }

    public void update(ArrayList<Fish> fishes) {
        if (counter % cooldownFrames == 0)  {
            for (int i = 0; i < fishes.size(); i++) {
                Fish fish = fishes.get(i);
                int diffX = fish.getX() - x;
                int diffY = fish.getY() - y;
                double distanceToTower =  Math.sqrt(diffX * diffX + diffY * diffY);
                if (fishCount < 1) {
                    attack(fish, distanceToTower);
                    fishCount++;
                }
                if (i == fishes.size() - 1) {
                    fishCount = 0;
                }
            }
        }
        counter++;
    }

    public void attack(Fish fish, double distance) {
        if (distance <= range) {
            switch (currentDiff) {
                case MEDIUM:
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth()/2));
                    break;
                case HARD:
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth()/4));
                    break;
                default:
                    fish.setHealth(0);
                    break;
            }
        }
    }
}
