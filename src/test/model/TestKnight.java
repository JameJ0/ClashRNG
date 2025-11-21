package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestKnight {

    Knight knight;

    @BeforeEach
    void runBefore() {
        knight = new Knight("name",null);
    }

    @Test
    void testConstructor() {
        assertEquals(20, knight.getHealth());
        assertEquals(5, knight.getDefense());
        assertEquals(3, knight.getAttack());
        assertEquals(2, knight.getSpeed());
        assertEquals(50, knight.getMana());
        assertEquals("Knight", knight.toString());
    }

    @Test
    void testJson() {
        JSONObject jsKnight = knight.toJson();
        assertEquals("Knight", jsKnight.getString("type"));
    }
}
