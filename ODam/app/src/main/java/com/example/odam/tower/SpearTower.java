package com.example.odam.tower;

import com.example.odam.FirstMapActivity;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Shark;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;

public class SpearTower extends Tower {
    private int counter = 0;
    private int cooldownFrames;
    private int fishCount = 30;
    private int fishCountMax = 2;
    public SpearTower(Difficulty diff) {
        name = "Spearman";
        imageID = R.drawable.spearman1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        switch (diff) {
        case MEDIUM:
            cost = 550;
            break;
        case HARD:
            cost = 650;
            break;
        default:
            cost = 450;
            break;
        }
        range = 300;
        cooldownFrames = (int) (1 * FirstMapActivity.getFps());
    }

    public void update(ArrayList<Fish> fishes) {
        fishCount = 0;
        if (counter % cooldownFrames == 0)  {
            for (int i = 0; i < fishes.size(); i++) {
                Fish fish = fishes.get(i);
                int diffX = fish.getX() - x;
                int diffY = fish.getY() - y;
                double distanceToTower =  Math.sqrt(diffX * diffX + diffY * diffY);
                if (fishCount < 2 && !fish.isDead()) {
                    attack(fish, distanceToTower);
                    fishCount++;
                }
            }
        }
        counter++;
    }

    public void attack(Fish fish, double distance) {
        if (distance <= range) {
            if (fish.getClass() == Shark.class) {
                fish.setHealth((int) (fish.getHealth() - (damage*2)));
            } else {
                fish.setHealth((int) (fish.getHealth() - damage));
            }
        }
    }

    public void upgradeOne() {
        damage = 60;
        imageID = R.drawable.spearman2;
    }

    public void upgradeTwo() {
        fishCountMax = 4;
        imageID = R.drawable.spearman3;
    }

    public void upgradeThree() {
        cooldownFrames = (int) (0.5 * FirstMapActivity.getFps());
        imageID = R.drawable.spearman3;
    }
}
