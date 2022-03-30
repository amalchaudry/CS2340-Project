package com.example.odam.fish;

import android.util.Log;

public abstract class Fish {
    protected String fishType;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int health;
    protected int speed;
    protected int imageID;
    protected int nextCheckpoint = 0;
    protected int[][] checkpoints = {
            {1120, 250},
            {770, 615},
            {145, 790},
            {560, 970},
            {1230, 870},
            {1300, 1080}
        };
    
    public Fish () {
        x = 0;
        y = 180;
        width = 1;
        height = 1;
    }

    public void update() {
        if (nextCheckpoint >= checkpoints.length) {
            return;
        }
        int diffX = checkpoints[nextCheckpoint][0] - x;
        int diffY = checkpoints[nextCheckpoint][1] - y;
        double magnitude = Math.sqrt(diffX * diffX + diffY * diffY);
        double dirX = (diffX / magnitude);
        double dirY = (diffY / magnitude);
//        Log.d("length", String.valueOf(checkpoints.length));
//        Log.d("diffY", String.valueOf(diffY));
        swim(dirX, dirY);
    }

    public void swim(double dirX, double dirY) {
        int targetX = checkpoints[nextCheckpoint][0];
        int targetY = checkpoints[nextCheckpoint][1];
        int newX = (int) (x + dirX * speed);
        int newY = (int) (y + dirY * speed);

        if (((targetX <= x && targetX >= newX)
                || (targetX >= x && targetX <= newX)
                && (targetY <= y && targetY >= newY)
                || (targetY >= y && targetY <= newY))) {
            x = targetX;
            y = targetY;
            nextCheckpoint++;
        } else {
            x = newX;
            y = newY;
        }
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getHealth () {
        return health;
    }

    public void setHealth() {
        this.health = health;
    }

    public int getImage() {
        return imageID;
    }

    public void setImage(int imageID) {
        this.imageID = imageID;
    }


    public void attackMon() {
        //check position and decrease lake HP
        return;
    }
}