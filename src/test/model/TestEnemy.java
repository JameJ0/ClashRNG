package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestEnemy {

    Enemy enemy;

    @BeforeEach
    void runBefore() {
        enemy = new Enemy(5, 1, 2, 3, 4);
    }

    @Test
    void testConstructor() {
        assertEquals(5, enemy.getLoot());
    }

    @Test
    void testGenerateDrop() {
        Item i = enemy.generateDrop(0.5);
        assertEquals(Rarity.LEGENDARY, i.getRarity());
        i = enemy.generateDrop(2);
        assertEquals(Rarity.EPIC, i.getRarity());
        i = enemy.generateDrop(4);
        assertEquals(Rarity.RARE, i.getRarity());
        i = enemy.generateDrop(15);
        assertEquals(Rarity.UNCOMMON, i.getRarity());
        i = enemy.generateDrop(30);
        assertEquals(Rarity.COMMON, i.getRarity());
    }  
}
