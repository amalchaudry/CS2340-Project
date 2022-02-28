package com.example.odam;

public class FishermanTower extends Tower{
    //TODO: Integrate difficulty with tower cost
    public FishermanTower(Difficulty difficulty) {
        name = "Fisherman";
        imageID = R.drawable.fisherman_with_rod;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 10;
        cost = 30;
        range = 100;
        cooldown = 2;
    }
}
