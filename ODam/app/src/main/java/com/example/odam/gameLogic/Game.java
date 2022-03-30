package com.example.odam.gameLogic;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.tower.Tower;

public class Game {
    private Difficulty diff;
    private Shop shop;
    private Player player;
    private ArrayList<Fish> fishArr = new ArrayList<Fish>(new Tuna());

    public Game(Difficulty diff) {
        this.diff = diff;
        Log.d("Diff", diff.toString());
        int money = 0;
        int lakeHP = 0;

        switch (diff) {
        case MEDIUM:
            money = 900;
            lakeHP = 150;
            break;
        case HARD:
            money = 800;
            lakeHP = 100;
            break;
        default:
            money = 1000;
            lakeHP = 200;
            break;
        }

        player = new Player(money, lakeHP);
        shop = new Shop();
    }

    public void selectNewTower(Tower tower) {
        shop.chooseNewTower(tower);
    }

    public void deselectTower() { shop.stopChoosingTower(); }

    public boolean isPlacingChosenTower() { return shop.isPlacingChosenTower(); }

    public boolean canBuyChosenTower() {
        return  shop.canBuyChosenTower(player.getMoney());
    }

    public boolean canPlaceChosenTower(float eventX, float eventY, Bitmap bitmap) {
        int bitmapOffsetX = 220;
        int bitmapOffsetY = 25;
        int viewableWidth = 1980;
        int viewableHeight = 1075;
        int mapWidth = 1530;
        int mapHeight = viewableHeight;
        int bitmapWidth = 3300;
        int bitmapHeight = 1640;
        float trueX = eventX - bitmapOffsetX;
        float trueY = eventY - bitmapOffsetY;
        boolean isWithinBoundsX = (0 <= trueX && trueX <= mapWidth);
        boolean isWithinBoundsY = (0 <= trueY && trueY <= mapHeight);
        if (isPlacingChosenTower() && isWithinBoundsX && isWithinBoundsY) {
            int finalX = (int) (trueX / viewableWidth * bitmapWidth);
            int finalY = (int) (trueY / viewableHeight * bitmapHeight);
            int pixel = bitmap.getPixel(finalX, finalY);
            float[] hsv = new float[3];
            Color.colorToHSV(pixel, hsv);
            int lowerboundBlue = 180;
            int upperboundBlue = 200;
            return hsv[0] < lowerboundBlue  ||  upperboundBlue < hsv[0];
        }
        return false;
    }

    public Tower getChosenTower() {
        return shop.getChosenTower();
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public void setPlacingTower(boolean placingTower) {
        shop.setPlacingTower(placingTower);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerMoney(int money) {
        player.setMoney(money);
    }

    public void startCombat() {
        Timer timer = new Timer();
        Timer timer2 = new Timer();
        Random rand = new Random();
        int fishCounter = 0;

        // inst. task for updating coordinates
        TimerTask updateTask = new TimerTask() {
            @Override
            public void run() {
                int index = rand.nextInt(2);
                if (index == 1) {
                    fishArr.add(new Swordfish());
                }

                if (index == 2) {
                    fishArr.add(new Tuna());
                }

                if (index == 3) {
                    fishArr.add(new Salmon());
                }
            }
        };

        // inst. task for adding new fish onto map
        TimerTask addFishTask = new TimerTask() {
            @Override
            public void run() {
                if (fishCounter >= 15) {
                    timer2.cancel();
                }
                int index = rand.nextInt(2);
                if (index == 1) {
                    fishArr.add(new Swordfish());
                }

                if (index == 2) {
                    fishArr.add(new Tuna());
                }

                if (index == 3) {
                    fishArr.add(new Salmon());
                }
            }
        };

        // update coordinate of fish time
        // delay: Schedules the specified task for execution after the specified delay.
        // delay: in milliseconds before task is to be executed.
        timer.scheduleAtFixedRate(updateTask, 1000, 1000);
        // add fish until the 15th fish is added, then stop adding new fish
        timer2.scheduleAtFixedRate(addFishTask, 1000, 1000);


    }
}
