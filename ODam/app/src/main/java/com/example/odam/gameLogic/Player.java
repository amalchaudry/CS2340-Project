package com.example.odam.gameLogic;

public class Player {
    private int money;
    private int lakeHP;
    private int fishesCaught = 0;
    private int deadFish = 0;

    public Player(int money, int lakeHP) {
        this.lakeHP = lakeHP;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getLakeHP() {
        return lakeHP;
    }

    public void setLakeHP(int lakeHP) {
        this.lakeHP = lakeHP;
    }

    public int getFishesCaught() {
        return fishesCaught;
    }

    public void setFishesCaught(int fishesCaught) {
        this.fishesCaught = fishesCaught;
    }

    public int getDeadFish() {
        return deadFish;
    }

    public void setDeadFish(int deadFish) {
        this.deadFish = deadFish;
    }

}
