package model;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(2, goblin.getSpeed());
        assertEquals(1, goblin.getDefense());
        assertEquals(4, goblin.getAttack());
        assertEquals(0, goblin.getMana());
        assertEquals(5, goblin.getLoot());
    }

    @Test
    void testToString() {
        assertEquals("Goblin", goblin.toString());
    }
}
