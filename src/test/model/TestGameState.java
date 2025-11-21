package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestGameState {

    GameState gs;
    Player player;
    List<Enemy> enemies;

    @BeforeEach
    void runBefore() {
        gs = new GameState();
        player = new Knight("name");
        enemies = new ArrayList<>();
    }

    @Test
    void testSetPlayer() {
        gs.setPlayer(player);
        assertEquals(player, gs.getPlayer());
    }

    @Test
    void testSetEnemies() {
        enemies.add(new Goblin());
        enemies.add(new Goblin());
        gs.setEnemies(enemies);
        assertEquals(2, gs.getEnemies().size());
    }

    @Test
    void testJson() {
        enemies.add(new Goblin());
        enemies.add(new Goblin());
        enemies.add(new Goblin());
        gs.setEnemies(enemies);
        gs.setPlayer(player);
        JSONObject jsgs = gs.toJson();
        JSONArray jsEnemies = jsgs.getJSONArray("enemies");
        assertEquals(3, jsEnemies.length());
        JSONObject playerFromJson = jsgs.getJSONObject("player");
        assertEquals("name", playerFromJson.getString("name"));
        assertEquals(50, playerFromJson.getInt("mana"));
    }
}
