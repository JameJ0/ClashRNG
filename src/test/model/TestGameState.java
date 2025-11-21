package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.*;

import model.GameState.Status;

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
        assertEquals(1, player.getX());
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
        for (int i = 0; i < 100; i++) {
            gs.generateDrop(goblin);
        }
        assertTrue(gs.getDroppedItems().size() >= 1);
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
