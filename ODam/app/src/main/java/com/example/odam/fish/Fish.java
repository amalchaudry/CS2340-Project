package com.example.odam.fish;

public abstract class Fish {
    int x;
    int y;
    int health;
    int speed;

    public abstract int getX();
    public abstract int getY();
    public abstract void setX();
    public abstract void setY();
    public abstract int getHealth();
    public abstract void setHealth();
    public abstract void swim();
    public abstract void attackMon();
}
