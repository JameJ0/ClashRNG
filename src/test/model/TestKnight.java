package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestKnight {

    Knight k;

    @BeforeEach
    void runBefore() {
        k = new Knight();
    }

    @Test
    void testConstructor() {
        assertEquals(20, k.getHealth());
        assertEquals(2, k.getSpeed());
        assertEquals(3, k.getDefense());
        assertEquals(5, k.getAttack());
        assertEquals(50, k.getMana());
        assertEquals(0, k.getLoot());
    }

    @Test
    void testToString() {
        assertTrue(k.toString().equals("Knight"));
    }
}
