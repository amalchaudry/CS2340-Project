package com.example.odam.gameLogic;

public class Player {
    private int money;
    private int lakeHP;
    private int fishesCaught = 0;

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
}
