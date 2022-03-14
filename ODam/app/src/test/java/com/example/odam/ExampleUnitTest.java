package com.example.odam;
import android.app.Application;
import org.junit.Test;
import
import static org.junit.Assert.*;

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
@RunWith(AndroidJUnit4.class)
public class startUnitTest {
    /**
     * This tests m3
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
     * This tests m3
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
     * This tests m3
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
     * This tests m2
     */
    @Test
    public void testWhiteSpaceonName() {
        GameApplication test = new GameApplication();
        test.setName("     ");
        assertEquals(test.getName(), "");
    }
    @Test
    public void differentLakeHP() {
        Game test = new Game(Difficulty.HARD);
        assertEquals(100, test.getPlayer().getLakeHP());
        Game test = new Game(Difficulty.MEDIUM);
        assertEquals(150, test.getPlayer().getLakeHP());
        Game test = new Game(Difficulty.EASY);
        assertEquals(200, test.getPlayer().getLakeHP());
    }
    @Test
    public void differentMoney() {
        Game test = new Game(Difficulty.HARD);
        assertEquals(800, test.getPlayer().getMoney());
        Game test = new Game(Difficulty.MEDIUM);
        assertEquals(900, test.getPlayer().getMoney());
        Game test = new Game(Difficulty.EASY);
        assertEquals(1000, test.getPlayer().getMoney());
    }
    @Test
    public void allSameDamage() {
        BoatTower test = new BoatTower(Difficulty.HARD);
        SpearTower spearman = new SpearTower(Difficulty.HARD);
        FishermanTower fishGuy = new FishermanTower(Difficulty.HARD);
        assertTrue(test.getDamage() == spearman.getDamage());
        assertTrue(fishGuy.getDamage() == spearman.getDamage());
        assertTrue(fishGuy.getDamage() == test.getDamage());
    }
    @Test
    public void gameInstantiatesPlayerObject() {
        Game test = new Game(Difficulty.HARD);
        assertNotNull(test.getPlayer());
        assertEquals(test.getPlayer().getMoney(), 800);
        assertEquals(test.getPlayer().getLakeHP(), 100);
    }
    @Test
    public void cannotPlaceTowerOutOfMap() {
        Game test = new Game(Difficulty.HARD);
        test.chooseNewTower(new BoatTower(Difficulty.HARD));
        assertFalse(test.canPlaceChosenTower(100000,100000, null);
    }
    @Test
    public void canBuyTower() {
        Game test = new Game(Difficulty.MEDIUM);
        test.chooseNewTower(new BoatTower(Difficulty.MEDIUM));
        assertTrue(test.canBuyChosenTower());
        Game second = new Game(Difficulty.HARD);
        second.chooseNewTower(new BoatTower(Difficulty.HARD));
        assertFalse(test.canBuyChosenTower());
    }
}
