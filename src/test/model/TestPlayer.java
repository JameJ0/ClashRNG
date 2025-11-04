package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestPlayer {

    Player player;

    @BeforeEach
    void runBefore() {
        player = new Player("name", 1, 2, 3, 4, 5);
    }

    @Test
    void testConstructor() {
        assertEquals("name", player.getName());
        assertEquals(5, player.getMana());
        assertEquals(0, player.getLoot());
    }

    @Test
    void testSetMana() {
        player.setMana(2);
        assertEquals(2, player.getMana());
    }


    @Test
    void testJson() {
        player.addLoot(5);
        player.setMana(3);
        JSONObject jsPlayer = player.toJson();
        assertEquals("name", jsPlayer.getString("name"));
        assertEquals(3, jsPlayer.getDouble("mana"));
    }
}
