package com.example.odam;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.odam.fish.Fish;
import com.example.odam.fish.Salmon;
import com.example.odam.fish.Swordfish;
import com.example.odam.fish.Tuna;
import com.example.odam.gameLogic.Difficulty;
import com.example.odam.gameLogic.Game;
import com.example.odam.gameLogic.GameApplication;
import com.example.odam.gameLogic.Player;
import com.example.odam.tower.BoatTower;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;

import org.junit.Test;

import java.util.Timer;

public class Milestone4UnitTests {

    /**
     * This tests if the health goes down by 10 every time fish pass monument
     */
    @Test
    public void HPDecreases() {
        Player player = new Player(600, 100);
        Fish fish = new Swordfish();
        fish.setCheckpoint(7);
        fish.update(player);
        assertEquals(90, player.getLakeHP());
    }
    /**
     * This tests if addFish() adds fish into array
     */
    @Test
    public void differentTowersDifferentRange() {
        Game game = new Game(Difficulty.HARD);
        Fish fish = new Tuna();
        Timer time = new Timer();
        game.addFish(time);
        assertEquals(game.getFishArr().size(), 1);
    }
    /**
     * This tests that the fishes move correctly
     */
    @Test
    public void fishMovement() {
        Player player = new Player(600, 100);
        Fish fish = new Swordfish();
        fish.setCheckpoint(5);
        fish.update(player);
        assertEquals(fish.getX(), 770);
        assertEquals(fish.getY(), 615);
        fish.setCheckpoint(3);
        fish.update(player);
        assertEquals(fish.getX(), 560);
        assertEquals(fish.getY(), 970);
        fish.setCheckpoint(1);
        fish.update(player);
        assertEquals(fish.getX(), 1300);
        assertEquals(fish.getY(), 1080);
    }
    /**
     * This tests different fishes move the same
     */
    @Test
    public void DifferentfishesMoveSame() {
        Player player = new Player(600, 100);
        Fish fish = new Swordfish();
        Fish fish2 = new Tuna();
        fish.setCheckpoint(5);
        fish.update(player);
        assertEquals(fish.getX(), fish2.getX());
        assertEquals(fish.getY(), fish2.getY());
    }
    /**
     * Tests when fishes are done that the correct flag is flagged
     */
    @Test
    public void checkCheckpoint() {
        Player player = new Player(600, 100);
        Fish fish = new Swordfish();
        fish.setCheckpoint(80);
        fish.update(player);
        assertFalse(!fish.getIsFinished());
    }
    /**
     *  Tests fish images
     */
    @Test
    public void fishImages() {
        Fish fish = new Swordfish();
        Fish fish2 = new Tuna();
        Fish fish3 = new Salmon();
        assertEquals(fish3.getImage(), R.drawable.fish_salmon);
        assertEquals(fish2.getImage(), R.drawable.fish_tuna);
        assertEquals(fish.getImage(), R.drawable.fish_swordfish);
    }

    /**
     * Tests the health set for the fish
     */
    @Test
    public void checkHealth() {
        Fish tuna = new Tuna();
        Fish salmon = new Salmon();
        Fish swordf = new Swordfish();
        assertEquals(100, tuna.getHealth());
        assertEquals(100, salmon.getHealth());
        assertEquals(100, swordf.getHealth());
    }

    /**
     * Tests the speed set for the fish
     */
    @Test
    public void checkSpeed() {
        Fish tuna = new Tuna ();
        Fish salmon = new Salmon();
        Fish swordf = new Swordfish();
        assertEquals(50, tuna.getSpeed());
        assertEquals(50, salmon.getHealth());
        assertEquals(50, swordf.getHealth());
    }

}
