package com.example.odam.gameLogic;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.odam.tower.Tower;

public class Shop {
    private boolean isPlacingTower = false;
    private Tower chosenTower;

    public Shop()  {

    }

    public void chooseNewTower(Tower tower) {
        chosenTower = tower;
        isPlacingTower = true;
    }

    public void stopChoosingTower() {
        chosenTower = null;
        isPlacingTower = false;
    }

    public boolean isPlacingChosenTower() {
        return chosenTower != null && isPlacingTower;
    }

    public boolean canBuyChosenTower(int playerMoney) {
        return  playerMoney - chosenTower.getCost() >= 0;
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
        return chosenTower;
    }

    public void setPlacingTower(boolean placingTower) {
        isPlacingTower = placingTower;
    }

}
