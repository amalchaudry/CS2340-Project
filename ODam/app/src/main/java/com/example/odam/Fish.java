package com.example.odam;

public abstract class Fish {
    int x;
    int y;
    int health;
    int speed;

    public int getCoord () {
        x = this.x;
        y = this.y;
        return x;
    }
    public abstract int setCoord();

    public int getHealth () {
    health = this.health;
    return health;
    }
    public abstract int setHealth();

    public void swim() {

    }
    public void attackMon() {

    }

}
