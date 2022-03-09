package com.example.odam;

public class BoatTower extends Tower{
    //TODO: Integrate difficulty with tower cost
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
