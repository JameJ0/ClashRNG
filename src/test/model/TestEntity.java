package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestEntity {

    Entity entity;

    @BeforeEach
    void runBefore() {
        entity = new Entity();
    }

    @Test
    void testConstructor() {
        assertEquals(0, entity.getInventory().size());
        assertEquals(0, entity.getLoot());
    }

    @Test
    void testSetHealth() {
        entity.setHealth(0);
        assertEquals(0, entity.getHealth());
    }

    @Test
    void testAddLoot() {
        entity.addLoot(5);
        assertEquals(5, entity.getLoot());
        entity.addLoot(10);
        assertEquals(15, entity.getLoot());
    }
}
