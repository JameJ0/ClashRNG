package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.*;

public class TestGameState {

    GameState gs;
    Player player;
    List<Enemy> enemies;
    Enemy goblin;
    Enemy goblin2;

    @BeforeEach
    void runBefore() {
        gs = new GameState();
        player = new Knight("name", null);
        enemies = new ArrayList<>();
        goblin = new Goblin(null);
        goblin2 = new Goblin(null);
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
        goblin.setDX(1);
        goblin2.setDX(2);
        enemies.add(goblin);
        enemies.add(goblin2);
        gs.setEnemies(enemies);
        gs.setPlayer(player);
        gs.update();
        assertEquals(1, player.getX());
        assertEquals(1, enemies.get(0).getX());
        assertEquals(2, enemies.get(1).getX());
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
