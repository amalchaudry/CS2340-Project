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
import com.example.odam.fish.Shark;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.tower.Tower;

public class Game {
    private Difficulty diff;
    private Shop shop;
    private Player player;
    private Fish fish;
    private Tower viewedTower;
    private ArrayList<Fish> fishArr = new ArrayList<Fish>();
    private ArrayList<Tower> towerArr = new ArrayList<>();
    private View v;
    private boolean finalBoss = false;
    private boolean combatStarted = false;
    private boolean updateFish = false;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private int deathCounter = 0;
    private int fishKilled = 0;
    private int moneySpent = 0;
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
        int bitmapOffsetX = 260;
        int bitmapOffsetY = 155;
        int viewableWidth = 1980;
        int viewableHeight = 1075;
        int mapWidth = 1580;
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
        boolean gameWon = checkWin(fish);
        // easy solution is remove that fish from fish array
        for (int i = 0; i < fishArr.size(); i++) {
            Fish fish = fishArr.get(i);
            if (fish.getHealth() <= 0 && !fish.isDead()) {
                fish.setDead(true);
                fishKilled++;
                addMoney();
            } else {
                fish.update(player);
            }
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

    public boolean checkAddShark() {
        if (finalBoss) {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < fishArr.size(); i++) {
            if (fishArr.get(i).isDead() == true) {
                counter++;
            }
        }
        if (counter == fishArr.size() && fishArr.size() >= 16) {
            finalBoss = true;
            return true;
        }
        return false;
    }

    public Fish addShark() {
        if (checkAddShark()) {
            Fish newFish = new Shark();
            fishArr.add(newFish);
            return newFish;
        }
        return null;
    }

    public void addTower(Tower tower) {
        towerArr.add(tower);
    }

    public int moneySpent() {
        for (int i = 0; i <= towerArr.size(); i++) {
            moneySpent += towerArr.get(i).getCost();
        }
        return moneySpent;
    }

    public void addMoney() {
        int money = player.getMoney();
        money += 50;
        player.setMoney(money);
    }

    public boolean checkWin(Fish fish) {
        if (fish.getClass() == Shark.class) {
            if (fish.getHealth() == 0 && !fish.isDead()) {
                gameWon = true;
            } else {
                gameOver = true;
            }
        }
        return gameWon;
    }

    public boolean checkGameOver(Player player) {
        if (player.getLakeHP() <= 0 || fishArr.size() >= 17 && fishArr.get(16).isDead()) {
            deathCounter++;
            gameOver = true;
        }
        return gameOver;
    }

    public int getDeathCounter() {
        return deathCounter;
    }

    public boolean upgradeTower() {
        if (player.getMoney() < viewedTower.getCost()) {
            return false;
        }
        viewedTower.upgradeTower();
        int newMoney = player.getMoney() - viewedTower.getCost();
        player.setMoney(newMoney);
        return true;
    }

    public boolean getFinalBoss() {
        return finalBoss;
    }

    public int getFishCounter() {
        return fishCounter;
    }

    public void setFishCounter(int fishCount) {
        fishCounter = fishCount;
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

    public Tower getViewedTower() {
        return viewedTower;
    }

    public void setViewedTower(Tower viewedTower) {
        this.viewedTower = viewedTower;
    }

    public void setFinalBoss(boolean b) {
        finalBoss = b;
    }

    public void setFishArr(ArrayList<Fish> newFishArr) {
        fishArr = newFishArr;
    }

    public int getFishKilled() { return fishKilled; }
}
