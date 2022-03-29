package com.example.odam.tower;


import com.example.odam.gameLogic.TowerUpgradeLevel;

public abstract class Tower {
    protected String name;
    protected int imageID;      /**INT REPRESENTS RESOURCE ID IN DRAWABLE FOLDER
                                I.E. R.drawable.fisherman_with_rod returns an int
                                tower stores the drawable resource id,
                                imageview is constructed in activity code **/
    protected TowerUpgradeLevel upgradeLevel;
    protected float damage;
    protected int cost;
    protected float range;
    protected float cooldown;

    public Tower() {
        name = "";
        imageID = 0;
        upgradeLevel = TowerUpgradeLevel.BASE;
        damage = 0;
        cost = 0;
        range = 0;
        cooldown = 0;
    }
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
}
