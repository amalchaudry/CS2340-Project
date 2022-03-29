package com.example.odam.tower;

import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

public class BoatTower extends Tower {
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
        range = 150;
        cooldown = 2;
    }
}
