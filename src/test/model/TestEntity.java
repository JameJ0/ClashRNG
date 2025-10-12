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
        assertEquals(0, e.getLoot());
    }

    @Test
    void testSetHealth() {
        e.setHealth(0);
        assertEquals(0, e.getHealth());
    }

    @Test
    void testAddLoot() {
        e.addLoot(5);
        assertEquals(5,e.getLoot());
        e.addLoot(10);
        assertEquals(15,e.getLoot());
    }
}
