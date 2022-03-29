package com.example.odam.fish;

public class Salmon extends Fish {
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    public void setX() {
        x = getX();
        return;
    }

    @Override
    public void setY() {
        y = getY();
        return;
    }

    @Override
    public int getHealth () {
        return this.health;
    }

    @Override
    public void setHealth() {
        health = getHealth();
        return;
    }

    @Override
    public void swim() {
        return;
    }

    @Override
    public void attackMon() {
        //check position and decrease lake HP
        return;
    }

}
