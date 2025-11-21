package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestBandit {

    Bandit bandit;

    @BeforeEach
    void runBefore() {
        bandit = new Bandit("name");
    }

    @Test
    void testConstructor() {
        assertEquals(15, bandit.getHealth());
        assertEquals(10, bandit.getAttack());
        assertEquals(1, bandit.getDefense());
        assertEquals(6, bandit.getSpeed());
        assertEquals(50, bandit.getMana());
        assertEquals("Bandit", bandit.toString());
    }

    @Test
    void testJson() {
        JSONObject jsBandit = bandit.toJson();
        assertEquals("Bandit", jsBandit.getString("type"));
    }

}
