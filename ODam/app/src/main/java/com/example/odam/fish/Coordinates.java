package com.example.odam.fish;

public class Coordinates {
    protected int x;
    protected int y;
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public double getDirX(int diffX, int diffY) {
        double magnitude = Math.sqrt(diffX * diffX + diffY * diffY);
        double dirX = (diffX / magnitude);
        return dirX;
    }
    public double getDirY(int diffX, int diffY) {
        double magnitude = Math.sqrt(diffX * diffX + diffY * diffY);
        double dirY = (diffY / magnitude);
        return dirY;
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
