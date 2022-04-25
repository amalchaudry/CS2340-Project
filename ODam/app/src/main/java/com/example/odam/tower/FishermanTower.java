package com.example.odam.tower;

import com.example.odam.FirstMapActivity;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Shark;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;

public class FishermanTower extends Tower {
    private int cooldownFrames = (int) (2.5 * FirstMapActivity.getFps());
    private int fishCount = 0;
    private int fishCountMax = 1;
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
        fishCount = 0;
        if (counter % cooldownFrames == 0)  {
            for (int i = 0; i < fishes.size(); i++) {
                Fish fish = fishes.get(i);
                int diffX = fish.getX() - x;
                int diffY = fish.getY() - y;
                double distanceToTower =  Math.sqrt(diffX * diffX + diffY * diffY);
                if (fishCount < fishCountMax && !fish.isDead()) {
                    attack(fish, distanceToTower);
                    fishCount++;
                }
            }
        }
        counter++;
    }

    public void attack(Fish fish, double distance) {
        if (distance <= range) {
            switch (currentDiff) {
            case MEDIUM:
                if (fish.getClass() == Shark.class) {
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth() / 4));
                } else {
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth() / 2));
                }
                break;
            case HARD:
                if (fish.getClass() == Shark.class) {
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth() / 6));
                } else {
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth() / 4));
                }
                break;
            default:
                if (fish.getClass() == Shark.class) {
                    fish.setHealth(fish.getHealth() - (fish.getMaxHealth() / 2));
                } else {
                    fish.setHealth(0);
                }
                break;
            }
        }
    }

    public void upgradeOne() {
        fishCountMax = 2;
        imageID = R.drawable.fisherman2;
    }

    public void upgradeTwo() {
        fishCountMax = 3;
        imageID = R.drawable.fisherman3;
    }

    public void upgradeThree() {
        fishCountMax = 5;
        imageID = R.drawable.fisherman3;
    }

    public int getFishCountMax() {
        return fishCountMax;
    }
}
