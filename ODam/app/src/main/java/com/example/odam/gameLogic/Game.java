package com.example.odam.gameLogic;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.example.odam.tower.Tower;

public class Game {
    private Difficulty diff;
    private Shop shop;
    private Player player;

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
        //TODO: functionality @Tina
    }
}
