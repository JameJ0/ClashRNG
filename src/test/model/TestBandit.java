package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestBandit {

    Bandit b;

    @BeforeEach
    void runBefore() {
        b = new Bandit();
    }

    @Test
    void testConstructor() {
        assertEquals(15, b.getHealth());
        assertEquals(5, b.getSpeed());
        assertEquals(1, b.getDefense());
        assertEquals(10, b.getAttack());
        assertEquals(50, b.getMana());
        assertEquals(0, b.getLoot());
    }

    @Test
    void testToString() {
        assertEquals("Bandit", b.toString());
    }
}
