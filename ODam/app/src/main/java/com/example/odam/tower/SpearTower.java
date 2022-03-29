package com.example.odam.tower;

import com.example.odam.gameLogic.Difficulty;
import com.example.odam.R;
import com.example.odam.gameLogic.TowerUpgradeLevel;

public class SpearTower extends Tower {
    public SpearTower(Difficulty diff) {
        name = "Spearman";
        imageID = R.drawable.spearman1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        switch (diff) {
        case MEDIUM:
            cost = 850;
            break;
        case HARD:
            cost = 950;
            break;
        default:
            cost = 750;
            break;
        }
        range = 300;
        cooldown = 3;
    }
}
