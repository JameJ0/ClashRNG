package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestKnight {

    Knight knight;

    @BeforeEach
    void runBefore() {
        knight = new Knight();
    }

    @Test
    void testConstructor() {
        assertEquals(20, knight.getHealth());
        assertEquals(2, knight.getSpeed());
        assertEquals(3, knight.getDefense());
        assertEquals(5, knight.getAttack());
        assertEquals(50, knight.getMana());
        assertEquals(0, knight.getLoot());
    }

    @Test
    void testToString() {
        assertTrue(knight.toString().equals("Knight"));
    }
}
