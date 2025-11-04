package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestGoblin {

    Goblin goblin;

    @BeforeEach
    void runBefore() {
        goblin = new Goblin();
    }

    @Test
    void testConstructor() {
        assertEquals(15, goblin.getHealth());
        assertEquals(4, goblin.getAttack());
        assertEquals(1, goblin.getDefense());
        assertEquals(2, goblin.getSpeed());
        assertEquals("Goblin", goblin.toString());
    }

    @Test
    void toJson() {
        JSONObject jsGoblin = goblin.toJson();
        assertEquals("Goblin", jsGoblin.getString("type"));
    }
}
