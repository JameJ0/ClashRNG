package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestGoblin {

    Goblin g;

    @BeforeEach
    void runBefore() {
        g = new Goblin();
    }

    @Test
    void testConstructor() {
        assertEquals(15, g.getHealth());
        assertEquals(2, g.getSpeed());
        assertEquals(1, g.getDefense());
        assertEquals(4, g.getAttack());
        assertEquals(0, g.getMana());
        assertEquals(5, g.getLoot());
    }

    @Test
    void testToString() {
        assertEquals("Goblin", g.toString());
    }
}
