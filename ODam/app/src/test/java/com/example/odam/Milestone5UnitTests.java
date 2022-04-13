package com.example.odam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.gameLogic.Game;
import com.example.odam.gameLogic.Player;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;
import com.example.odam.tower.Tower;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Timer;

public class Milestone5UnitTests {

    /**
     * This tests if only a max of 2 fish get hit when in range
     */
    @Test
    public void multipleFishHPDecreaseST() {
        Fish fish1 = new Swordfish();
        Fish fish2 = new Tuna();
        Fish fish3 = new Salmon();
        SpearTower spear = new SpearTower(Difficulty.EASY);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish1);
        arrFish.add(fish2);
        arrFish.add(fish3);
        spear.update(arrFish);
        assertEquals(70, arrFish.get(0).getHealth());
        assertEquals(70, arrFish.get(1).getHealth());
        assertEquals(100, arrFish.get(2).getHealth());
    }

    /**
     * This tests if health of fish decreases by 30 when in range
     */
    @Test
    public void fishHPDecreaseST() {
        Fish fish1 = new Swordfish();
        SpearTower spear = new SpearTower(Difficulty.EASY);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish1);
        spear.update(arrFish);
        assertEquals(70, arrFish.get(0).getHealth());
    }

    /**
     * Tests if only 1 fish is killed by fisherman.
     */
    @Test
    public void multipleFishHPDecreaseFT() {
        Fish fish1 = new Swordfish();
        Fish fish2 = new Tuna();
        Fish fish3 = new Salmon();
        FishermanTower man = new FishermanTower(Difficulty.EASY);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish1);
        arrFish.add(fish2);
        arrFish.add(fish3);
        man.update(arrFish);
        assertEquals(0, arrFish.get(0).getHealth());
        assertEquals(100, arrFish.get(1).getHealth());
        assertEquals(100, arrFish.get(2).getHealth());
    }

    /**
     * Tests if fish health is correctly affected based on difficulty.
     */
    @Test
    public void diffDiffTest() {
        Fish fish = new Tuna();
        FishermanTower man = new FishermanTower(Difficulty.MEDIUM);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish);
        man.update(arrFish);
        assertEquals(50, fish.getHealth());
    }
}
