package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestEntity {

    Entity entity;
    Item item; 

    @BeforeEach
    void runBefore() {
        entity = new Goblin();
        item = new Item("name", 0, Rarity.COMMON);
    }

    @Test
    void testConstructor() {
        assertEquals(1, entity.getHealth());
        assertEquals(2, entity.getAttack());
        assertEquals(3, entity.getDefense());
        assertEquals(4, entity.getSpeed());
        assertEquals(0, entity.getLoot());
        assertEquals(0, entity.getInventory().size());
    }

    @Test
    void testSetHealth() {
        entity.setHealth(0);
        assertEquals(0, entity.getHealth());
    }

    @Test
    void testTakeDamage() {
        entity.takeDamage(1);
        assertEquals(0, entity.getHealth());
        entity.takeDamage(1);
        assertEquals(0, entity.getHealth());
    }

    @Test
    void testAddToInventory() {
        entity.addToInventory(item);
        assertEquals(1, entity.getInventory().size());
    }

    @Test
    void testAddLoot() {
        entity.addLoot(1);
        assertEquals(1, entity.getLoot());
    }

    @Test
    void testJson() {
        entity.addToInventory(item);
        JSONObject jsEntity = entity.toJson();
        JSONArray jsArr = jsEntity.getJSONArray("inventory");
        assertEquals(1, jsArr.length());
        assertEquals(1, jsEntity.getDouble("health"));
        assertEquals(0, jsEntity.getInt("loot"));
    }
}
