package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestBandit {

    Bandit bandit;

    @BeforeEach
    void runBefore() {
        bandit = new Bandit();
    }

    @Test
    void testConstructor() {
        assertEquals(15, bandit.getHealth());
        assertEquals(5, bandit.getSpeed());
        assertEquals(1, bandit.getDefense());
        assertEquals(10, bandit.getAttack());
        assertEquals(50, bandit.getMana());
        assertEquals(0, bandit.getLoot());
    }

    @Test
    void testToString() {
        assertEquals("Bandit", bandit.toString());
    }
}
