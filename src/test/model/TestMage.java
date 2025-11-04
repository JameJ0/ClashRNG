package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestMage {

    Mage mage;

    @BeforeEach
    void runBefore() {
        mage = new Mage("name");
    }

    @Test
    void testConstructor() {
        assertEquals(10, mage.getHealth());
        assertEquals(6, mage.getAttack());
        assertEquals(3, mage.getDefense());
        assertEquals(2, mage.getSpeed());
        assertEquals(100, mage.getMana());
        assertEquals("Mage", mage.toString());
    }

    @Test
    void testToString() {
        JSONObject jsMage = mage.toJson();
        assertEquals("Mage", jsMage.getString("type"));
    }
}
