package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.*;

import model.GameState.Status;
import ui.Assets;

public class TestGameState {

    GameState gs;
    Player player;
    List<Enemy> enemies;
    Enemy goblin;
    Enemy goblin2;
    BufferedImage fakeSprite;

    @BeforeEach
    void runBefore() {
        fakeSprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        gs = new GameState();
        player = new Knight("name", fakeSprite);
        enemies = new ArrayList<>();
        goblin = new Goblin(fakeSprite);
        goblin2 = new Goblin(fakeSprite);
    }

    @Test
    void testConstructor() {
        gs.setStatus(Status.GAME_OVER);
        assertEquals(Status.GAME_OVER, gs.getStatus());
    }

    @Test
    void testSetPlayer() {
        gs.setPlayer(player);
        assertEquals(player, gs.getPlayer());
    }

    @Test
    void testSetEnemies() {
        enemies.add(goblin);
        enemies.add(goblin);
        gs.setEnemies(enemies);
        assertEquals(2, gs.getEnemies().size());
    }

    @Test
    void testLog() {
        gs.logGameStart();
        Iterator<Event> iterator = EventLog.getInstance().iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        assertTrue(counter > 0);
    }

    @Test
    void testJson() {
        enemies.add(goblin);
        enemies.add(goblin);
        enemies.add(goblin);
        gs.setEnemies(enemies);
        gs.setPlayer(player);
        JSONObject jsgs = gs.toJson();
        JSONArray jsEnemies = jsgs.getJSONArray("enemies");
        assertEquals(3, jsEnemies.length());
        JSONObject playerFromJson = jsgs.getJSONObject("player");
        assertEquals("name", playerFromJson.getString("name"));
        assertEquals(50, playerFromJson.getInt("mana"));
    }

    @Test
    void testUpdate() {
        player.setDX(1);
        player.setHealth(0);
        gs.clearAllEnemies();
        enemies.add(goblin);
        enemies.add(goblin2);
        goblin2.setX(1000);
        goblin.setHealth(1);
        gs.setEnemies(enemies);
        gs.setPlayer(player);
        gs.update();
        assertEquals(801, player.getX());
        assertEquals(Status.GAME_OVER, gs.getStatus());
    }

    @Test
    void testDiscardCommon() {
        gs.getPlayer().addToInventory(new Item("test", 0, Rarity.COMMON, null));
        gs.getPlayer().addToInventory(new Item("test", 0, Rarity.RARE, null));
        gs.discardCommonItems();
        assertEquals(1, gs.getPlayer().getInventory().size());
    }

    @Test
    void testItemCollection() {
        goblin.setX(0);
        goblin.setY(0);
        for (int i = 0; i < 1000; i++) {
            gs.generateDrop(goblin);
        }
        player.setX(0);
        player.setY(0);
        gs.setPlayer(player);
        for (int i = 0; i < 100; i++) {
            gs.update();
        }
        assertTrue(gs.getDroppedItems().size() == 0);
        assertTrue(player.getInventory().size() > 0);
    }

    @Test
    void testRespawn() {
        player.setX(0);
        player.setY(0);
        gs.setPlayer(player);
        for (int i = 0; i < 10000; i++) {
            gs.respawnEnemies();
        }
    }

    @Test
    void testItemCollectionNotCollectable() {
        goblin.setX(500);
        goblin.setY(500);
        for (int i = 0; i < 1000; i++) {
            gs.generateDrop(goblin);
        }
        player.setX(0);
        player.setY(0);
        gs.setPlayer(player);
        for (int i = 0; i < 100; i++) {
            gs.update();
        }
        assertTrue(gs.getDroppedItems().size() > 1);
        assertTrue(player.getInventory().size() == 0);
    }

    @Test
    void testEnemyDie() {
        goblin.setHealth(0);
        List<Enemy> test = new ArrayList<>();
        test.add(goblin);
        gs.setEnemies(test);
        gs.update();
        assertEquals(3, gs.getEnemies().size());
    }

    @Test
    void testCollision1() {
        List<Enemy> test = new ArrayList<>();
        goblin = new Goblin(Assets.getInstance().getGoblinSprite());
        player = new Knight("test", Assets.getInstance().getKnightSprite());
        goblin.setX(0);
        goblin.setY(0);
        test.add(goblin);
        gs.setEnemies(test);
        player.setX(0);
        player.setY(0);
        gs.setPlayer(player);
        gs.update();
        assertTrue(player.colliding(goblin));
    }

    @Test
    void testKey() {
        gs.setPlayer(player);
        gs.keyPressed(KeyEvent.VK_W);
        assertEquals(-2, player.getDY());
        gs.keyReleased(KeyEvent.VK_W);
        assertEquals(0, player.getDY());
        gs.keyPressed(KeyEvent.VK_S);
        assertEquals(2, player.getDY());
        gs.keyReleased(KeyEvent.VK_S);
        assertEquals(0, player.getDY());
        gs.keyPressed(KeyEvent.VK_A);
        assertEquals(-2, player.getDX());
        gs.keyReleased(KeyEvent.VK_A);
        assertEquals(0, player.getDX());
        gs.keyPressed(KeyEvent.VK_D);
        assertEquals(2, player.getDX());
        gs.keyReleased(KeyEvent.VK_D);
        assertEquals(0, player.getDX());
    }

}
