package com.example.odam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.odam.gameLogic.Difficulty;
import com.example.odam.gameLogic.Game;
import com.example.odam.tower.BoatTower;
import com.example.odam.tower.FishermanTower;
import com.example.odam.tower.SpearTower;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}*/
@RunWith(JUnit4.class)
public class Milestone3UnitTest {
    /**
     * This tests m3 towers cost
     */
    @Test
    public void towersCostDifferently() {
        BoatTower test = new BoatTower(Difficulty.HARD);
        assertEquals(test.getCost(), 1050);
        BoatTower test2 = new BoatTower(Difficulty.EASY);
        assertEquals(test2.getCost(), 850);
        BoatTower test3 = new BoatTower(Difficulty.MEDIUM);
        assertEquals(test3.getCost(), 950);
    }
    /**
     * This tests m3 towers different stats
     */
    @Test
    public void differentTowersDifferentRange() {
        BoatTower test = new BoatTower(Difficulty.HARD);
        SpearTower spearman = new SpearTower(Difficulty.HARD);
        FishermanTower fishGuy = new FishermanTower(Difficulty.HARD);
        assertFalse(test.getRange() == spearman.getRange());
        assertFalse(fishGuy.getRange() == spearman.getRange());
        assertFalse(fishGuy.getRange() == test.getRange());
    }
    /**
     * This tests m3 towers different stats
     */
    @Test
    public void differentTowersDifferentCooldown() {
        BoatTower test = new BoatTower(Difficulty.HARD);
        SpearTower spearman = new SpearTower(Difficulty.HARD);
        FishermanTower fishGuy = new FishermanTower(Difficulty.HARD);
        assertFalse(test.getCooldown() == spearman.getCooldown());
        assertFalse(fishGuy.getCooldown() == spearman.getCooldown());
        assertFalse(fishGuy.getCooldown() == test.getCooldown());
    }
    /**
     * This tests m2 name whitespace
     */
    @Test
    public void testWhiteSpaceonName() {
        GameApplication test = new GameApplication();
        test.setName("     ");
        assertEquals(test.getName(), "");
    }

    /**
     * This tests m2 different starting hp
     */
    @Test
    public void differentLakeHP() {
        Game test = new Game(Difficulty.HARD);
        assertEquals(100, test.getPlayer().getLakeHP());
        test = new Game(Difficulty.MEDIUM);
        assertEquals(150, test.getPlayer().getLakeHP());
        test = new Game(Difficulty.EASY);
        assertEquals(200, test.getPlayer().getLakeHP());
    }

    /**
     * This tests m2 different starting money
     */
    @Test
    public void differentMoney() {
        Game test = new Game(Difficulty.HARD);
        assertEquals(800, test.getPlayer().getMoney());
        test = new Game(Difficulty.MEDIUM);
        assertEquals(900, test.getPlayer().getMoney());
        test = new Game(Difficulty.EASY);
        assertEquals(1000, test.getPlayer().getMoney());
    }

    /**
     * m3 tower stats
     */
    @Test
    public void allSameDamage() {
        BoatTower test = new BoatTower(Difficulty.HARD);
        SpearTower spearman = new SpearTower(Difficulty.HARD);
        FishermanTower fishGuy = new FishermanTower(Difficulty.HARD);
        assertTrue(test.getDamage() == spearman.getDamage());
        assertTrue(fishGuy.getDamage() == spearman.getDamage());
        assertTrue(fishGuy.getDamage() == test.getDamage());
    }

    /**
     * tests player object functionality (m2)
     */
    @Test
    public void gameInstantiatesPlayerObject() {
        Game test = new Game(Difficulty.HARD);
        assertNotNull(test.getPlayer());
        assertEquals(test.getPlayer().getMoney(), 800);
        assertEquals(test.getPlayer().getLakeHP(), 100);
    }

    /**
     * tests placing towers out of the map (m3)
     */
    @Test
    public void cannotPlaceTowerOutOfMap() {
        Game test = new Game(Difficulty.HARD);
        test.selectNewTower(new BoatTower(Difficulty.HARD));
        assertFalse(test.canPlaceChosenTower(100000, 100000, null));
    }

    /**
     * tests purchasing of towers (m3)
     */
    @Test
    public void canBuyTower() {
        Game test = new Game(Difficulty.MEDIUM);
        test.selectNewTower(new FishermanTower(Difficulty.MEDIUM));
        assertTrue(test.canBuyChosenTower());
        Game second = new Game(Difficulty.HARD);
        second.selectNewTower(new BoatTower(Difficulty.HARD));
        assertFalse(second.canBuyChosenTower());
    }
}
