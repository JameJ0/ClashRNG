package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestEntity {

    Entity e;

    @BeforeEach
    void runBefore() {
        e = new Entity();
    }

    @Test
    void testConstructor() {
        assertEquals(0, e.getInventory().size());
    }

    @Test
    void testSetHealth() {
        e.setHealth(0);
        assertEquals(0, e.getHealth());
    }
}
