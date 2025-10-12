package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestMage {

    Mage mage;

    @BeforeEach
    void runBefore() {
        mage = new Mage();
    }

    @Test
    void testConstructor() {
        assertEquals(10, mage.getHealth());
        assertEquals(3, mage.getSpeed());
        assertEquals(0, mage.getDefense());
        assertEquals(15, mage.getAttack());
        assertEquals(100, mage.getMana());
        assertEquals(0, mage.getLoot());
    }

    @Test
    void testToString() {
        assertTrue(mage.toString().equals("Mage"));
    }
}
