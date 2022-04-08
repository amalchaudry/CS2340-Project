package com.example.odam.tower;

import android.util.Log;

import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

public class FishermanTower extends Tower {
    public FishermanTower(Difficulty diff) {
        name = "Fisherman";
        imageID = R.drawable.fisherman1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
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

    public void attack(Fish fish, double distance) {
        Log.d("attack status", "attacked fish");
        return;
    }
}
