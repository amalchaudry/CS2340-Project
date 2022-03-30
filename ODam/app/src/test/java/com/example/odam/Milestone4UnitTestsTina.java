package com.example.odam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.odam.fish.Fish;
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

public class Milestone4UnitTestsTina {

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
}
