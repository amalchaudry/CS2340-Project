package com.example.odam.gameLogic;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import java.util.ArrayList;
import java.util.Timer;
import java.util.Random;
import android.view.View;

import com.example.odam.FirstMapActivity;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.tower.Tower;

public class Game {
    private Difficulty diff;
    private Shop shop;
    private Player player;
    private ArrayList<Fish> fishArr = new ArrayList<Fish>();
    private ArrayList<Tower> towerArr = new ArrayList<>();
    private View v;

    private boolean combatStarted = false;
    private boolean updateFish = false;
    private boolean gameOver = false;
    private int fishCounter = 0; //represent fish generated in round


    //for junit testing
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

    public Game(Difficulty diff, FirstMapActivity fmp) {
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

    public void deselectTower() {
        shop.stopChoosingTower(); }

    public boolean isPlacingChosenTower() {
        return shop.isPlacingChosenTower(); }

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
        combatStarted = true;
    }

    public boolean getBoolean() {
        return gameOver; }

    public boolean update(Timer timer) {
        boolean gameOver = checkGameOver(player);
        if (player.getDeadFish() >= 15) {
            combatStarted = false;
            timer.cancel();
        }
        // easy solution is remove that fish from fish array
        for (int i = 0; i < fishArr.size(); i++) {
            fishArr.get(i).update(player);
        }
        for (int i = 0; i < towerArr.size(); i++) {
            towerArr.get(i).update(fishArr);
        }
        updateFish = true;
        return gameOver;
    }

    public Fish addFish(Timer timer) {
        Random rand = new Random();
        if (fishCounter >= 15) {
            timer.cancel();
        }
        int index = rand.nextInt(3);
        Fish newFish;
        if (index == 0) {
            newFish = new Swordfish();
        } else if (index == 1) {
            newFish = new Tuna();
        } else {
            newFish = new Salmon();
        }
        fishCounter++;
        fishArr.add(newFish);
        newFish.printHealth();
        return newFish;
    }

    public void addTower(Tower tower) {
        towerArr.add(tower);
    }

    public addMoney() {
        // for (int i = 0; i < fishArr.length(); i++) {
             for (fish : fishArr) {
                 if (fish.health == 0) {
                     int money = player.getMoney();
                     money += 50;
                     player.setMoney(player.getMoney() + money);
                 }
             }
     }

    public boolean checkGameOver(Player player) {
        if (player.getLakeHP() <= 0) {
            gameOver = true;
        }
        return gameOver;
    }
    public boolean isCombatStarted() {
        return combatStarted;
    }

    public void setCombatStarted(boolean combatStarted) {
        this.combatStarted = combatStarted;
    }


    public ArrayList<Fish> getFishArr() {
        return fishArr;
    }

    public boolean isUpdateFish() {
        return updateFish;
    }

    public void setUpdateFish(boolean updateFish) {
        this.updateFish = updateFish;
    }

    public ArrayList<Tower> getTowerArr() {
        return towerArr;
    }

    public void setTowerArr(ArrayList<Tower> towerArr) {
        this.towerArr = towerArr;
    }
}
