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
        for (int i = 0; i < 17; i++) {
            fish = new Tuna();
            fish.setDead(true);
            arrFish.add(fish);
        }
        game1.setFishCounter(16);
        game1.setFishArr(arrFish);
        game1.setFinalBoss(false);
        game1.addShark();
        assertEquals(Shark.class, arrFish.get(17).getClass());
    }



}
