package com.example.odam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.tower.BoatTower;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;
import com.example.odam.gameLogic.Player;

import org.junit.Test;

import java.beans.Transient;
import java.util.ArrayList;

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
     * Tests if only 1 fish is killed by fisherman tower.
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
        fish.setHealth(100);
        FishermanTower man2 = new FishermanTower(Difficulty.HARD);
        man2.update(arrFish);
        assertEquals(25, fish.getHealth());
    }
    /**
     * Tests if internal attack method works correctly.
     */
    @Test
    public void attackTestWithDistances() {
        Fish fish = new Tuna();
        FishermanTower man = new FishermanTower(Difficulty.MEDIUM);
        man.attack(fish, 600);
        assertEquals(100, fish.getHealth());
        man.attack(fish, 500);
        assertEquals(50, fish.getHealth());
    }
    /**
     * Tests successive calls to update method for combat
     */
    @Test
    public void updateTest() {
        Fish fish = new Tuna();
        FishermanTower man = new FishermanTower(Difficulty.MEDIUM);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish);
        man.update(arrFish);
        assertEquals(50, fish.getHealth());
        Fish fish1 = new Swordfish();
        arrFish.add(fish1);
        man.update(arrFish);
        assertEquals(0, fish.getHealth());
        assertEquals(100, fish1.getHealth());
    }

    /**
     * This tests if multiple fish in range are slowed
     */
    @Test
    public void multipleFishSpeedDecreaseBT() {
        Fish fish1 = new Swordfish();
        Fish fish2 = new Tuna();
        Fish fish3 = new Salmon();
        BoatTower boat = new BoatTower(Difficulty.EASY);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish1);
        arrFish.add(fish2);
        arrFish.add(fish3);
        boat.update(arrFish);
        assertEquals(7, arrFish.get(0).getSpeed());
        assertEquals(4, arrFish.get(1).getSpeed());
        assertEquals(3, arrFish.get(2).getSpeed());
    }

    /**
     * Tests if boat doesn't slow fish when out of range
     */
    @Test
    public void fishOutRangeSpeed() {
        Fish fish = new Swordfish();
        BoatTower boat = new BoatTower(Difficulty.EASY);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish);
        fish.setX(10000);
        fish.setY(10000);
        boat.update(arrFish);
        assertEquals(arrFish.get(0).getBaseSpeed(), arrFish.get(0).getSpeed());
    }

    /* This tests if money is added correctly after the fish dies */
    @Test 
    public void moneyAdd() {
        Player play = new Player(1000, 200);
        Fish fish = new Swordfish();
        FishermanTower fishMan = new FishermanTower(Difficulty.EASY);
        fish.setX(120);
        fish.setY(100);
        ArrayList<Fish> arrFish = new ArrayList<>();
        arrFish.add(fish);
        fishMan.setX(120);
        fishMan.setY(100);
        fishMan.update(arrFish);
        int newMoney = play.getMoney() + 50;
        assertTrue(arrFish.get(1).getHealth() <= 0);
        assertEquals(newMoney, play.getMoney());
    }

    /* This tests that the fish is removed from the fish array after being killed */
    @Test
    public void fishDead() {
    Player play = new Player(1000, 200);
    Fish fish = new Swordfish();
    FishermanTower fishMan = new FishermanTower(Difficulty.EASY);
    fish.setX(120);
    fish.setY(100);
    ArrayList<Fish> arrFish = new ArrayList<>();
    arrFish.add(fish);
    fishMan.setX(120);
    fishMan.setY(100);
    fishMan.update(arrFish);
    assertTrue(!arrFish.contains(fish));
    assertFalse(arrFish.contains(fish));
    }
}
