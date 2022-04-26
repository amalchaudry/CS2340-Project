package com.example.odam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Shark;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.tower.BoatTower;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;
import com.example.odam.gameLogic.Game;

import org.junit.Test;

import java.util.ArrayList;

public class Milestone6UnitTests {

    /**
     * This tests if checkAddShark returns false if not all fish dead
     */
    @Test
    public void noSharkAdded() {
        Fish fish;
        ArrayList<Fish> arrFish = new ArrayList<>();
        Game game1 = new Game(Difficulty.EASY);
        for (int i = 0; i < 16; i++) {
            fish = new Tuna();
            fish.setDead(true);
            arrFish.add(fish);
        }
        fish = new Tuna();
        fish.setDead(false);
        arrFish.add(fish);
        game1.setFishCounter(16);
        game1.setFishArr(arrFish);
        game1.setFinalBoss(false);
        assertEquals(false, game1.checkAddShark());
    }

    /**
     * This tests if shark is added effectively for addShark
     */
    @Test
    public void addFinalBoss() {
        Fish fish;
        ArrayList<Fish> arrFish = new ArrayList<>();
        Game game1 = new Game(Difficulty.EASY);
        for (int i = 0; i < 16; i++) {
            fish = new Tuna();
            fish.setDead(true);
            arrFish.add(fish);
        }
        game1.setFishCounter(16);
        game1.setFishArr(arrFish);
        game1.setFinalBoss(false);
        game1.addShark();
        assertEquals(Shark.class, arrFish.get(16).getClass());
    }

    /**
     * This tests if upgrade once works
     */
    @Test
    public void upgradeOnce() {
        FishermanTower fisherman = new FishermanTower(Difficulty.EASY);
        Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(fisherman);
        game1.upgradeTower();
        assertEquals(fisherman.getFishCountMax(), 2);
        assertEquals(fisherman.getImage(), R.drawable.fisherman2);
    }

    /**
     * This tests if upgrade twice works
     */
    @Test
    public void upgradeTwice() {
        FishermanTower fisherman = new FishermanTower(Difficulty.EASY);
        Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(fisherman);
        game1.upgradeTower();
        game1.upgradeTower();
        assertEquals(fisherman.getFishCountMax(), 3);
        assertEquals(fisherman.getImage(), R.drawable.fisherman3);
    }

    /**
     * Tests the upgrade feature and ensures that all of the upgrade images 
     * update for each tower.
     */
    @Test
    public void upgradeImage() {
        FishermanTower fishMan = new FishermanTower(Difficulty.EASY);
        Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(fishMan);
        game1.upgradeTower();
        assertEquals(fishMan.getImage(), R.drawable.fisherman2);

        BoatTower boatMan = new BoatTower(Difficulty.EASY);
        //Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(boatMan);
        game1.upgradeTower();
        assertEquals(boatMan.getImage(), R.drawable.boat2);

        SpearTower spearMan = new SpearTower(Difficulty.EASY);
        //Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(spearMan);
        game1.upgradeTower();
        assertEquals(spearMan.getImage(), R.drawable.spearman2);
    }

    /**
     * Tests the upgrade feature and ensures that tower can only update 3 times
     */
    @Test
    public void upgradeImage() {
        FishermanTower fishMan = new FishermanTower(Difficulty.EASY);
        Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(fishMan);
        game1.upgradeTower();
        game1.upgradeTower();
        game1.upgradeTower();
        assertEquals(fishMan.getImage(), R.drawable.fisherman3);

        BoatTower boatMan = new BoatTower(Difficulty.EASY);
        //Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(boatMan);
        game1.upgradeTower();
        game1.upgradeTower();
        game1.upgradeTower();
        assertEquals(boatMan.getImage(), R.drawable.boat3);

        SpearTower spearMan = new SpearTower(Difficulty.EASY);
        //Game game1 = new Game(Difficulty.EASY);
        game1.setViewedTower(spearMan);
        game1.upgradeTower();
        game1.upgradeTower();
        game1.upgradeTower();
        assertEquals(spearMan.getImage(), R.drawable.spearman3);
    }

    /**
     * 
     */

    @Test
    public void sharkDeath () {
        Shark sharkie = new Shark();

    }


}
