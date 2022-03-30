package com.example.odam.fish;

public abstract class Fish {
    String fishType;
    int x;
    int y;
    int health;
    int speed;
    
    public Fish () {
    fishType = "";
    x = 0;
    y = 0;
    health = 0;
    speed = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX() {
        this.x = x;
    }

    public void setY() {
        this.y = y;
    }

    public int getHealth () {
        return health;
    }

    public void setHealth() {
        this.health = health;
    }

    public void swim() {
        return;
    }

    public void attackMon() {
        //check position and decrease lake HP
        return;
    }
}
