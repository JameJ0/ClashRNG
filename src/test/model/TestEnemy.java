package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class TestEnemy {

    Enemy enemy;

    @BeforeEach
    public void runBefore() {
        enemy = new Goblin();
    }

    @Test
    public void testConstructor() {
        assertEquals(5, enemy.getLoot());
    }

    @Test
    public void testGenerateDrop() {
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
