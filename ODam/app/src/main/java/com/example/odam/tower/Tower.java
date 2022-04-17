package com.example.odam.tower;

import android.util.Log;
import com.example.odam.fish.Fish;
import com.example.odam.gameLogic.TowerUpgradeLevel;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Tower {
    protected String name;
    protected int imageID;      /**INT REPRESENTS RESOURCE ID IN DRAWABLE FOLDER
                                I.E. R.drawable.fisherman_with_rod returns an int
                                tower stores the drawable resource id,
                                imageview is constructed in activity code **/
    protected TowerUpgradeLevel upgradeLevel;
    protected HashMap<TowerUpgradeLevel, Runnable> upgradeMap = new HashMap<>();
    protected float damage;
    protected int cost;
    protected float range;
    protected float cooldown;
    protected int x;
    protected int y;
    protected int upgradeCost;

    public Tower() {
        name = "";
        imageID = 0;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 0;
        cost = 0;
        range = 0;
        cooldown = 0;
        upgradeCost = 300;
        upgradeMap.put(TowerUpgradeLevel.UPGRADE_ONE, () -> upgradeOne());
        upgradeMap.put(TowerUpgradeLevel.UPGRADE_TWO, () -> upgradeTwo());
        upgradeMap.put(TowerUpgradeLevel.UPGRADE_THREE, () -> upgradeThree());
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
            if (distanceToTower <= range) {
                attack(fish, distanceToTower);
            }
        }
    }

    public void upgradeTower() {
        upgradeLevel = upgradeLevel.next();
        upgradeMap.get(upgradeLevel).run();
    }
    //distance is should be the distance between them, calculated in update
    public abstract void attack(Fish fish, double distance);

    public abstract void upgradeOne();
    public abstract void upgradeTwo();
    public abstract void upgradeThree();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return imageID;
    }

    public void setImage(int image) {
        this.imageID = image;
    }

    public TowerUpgradeLevel getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(TowerUpgradeLevel upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
