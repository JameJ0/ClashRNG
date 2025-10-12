package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestMage {

    Mage m;

    @BeforeEach
    void runBefore() {
        m = new Mage();
    }

    @Test
    void testConstructor() {
        assertEquals(10, m.getHealth());
        assertEquals(3, m.getSpeed());
        assertEquals(0, m.getDefense());
        assertEquals(15, m.getAttack());
        assertEquals(100, m.getMana());
        assertEquals(0, m.getLoot());
    }

    @Test
    void testToString() {
        assertTrue(m.toString().equals("Mage"));
    }
}
