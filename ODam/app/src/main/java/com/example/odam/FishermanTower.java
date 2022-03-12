package com.example.odam;

public class FishermanTower extends Tower {
    public FishermanTower(Difficulty diff) {
        name = "Fisherman";
        imageID = R.drawable.fisherman1;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        switch (diff) {
        case MEDIUM:
            cost = 750;
            break;
        case HARD:
            cost = 850;
            break;
        default:
            cost = 650;
            break;
        }
        range = 50;
        cooldown = 1;
    }
}
