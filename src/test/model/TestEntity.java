package model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestEntity {

    Entity entity;
    Entity entity2;
    Item item;

    @BeforeEach
    void runBefore() {
        BufferedImage fakeSprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
        entity = new Goblin(fakeSprite);
        entity2 = new Goblin(null);
        item = new Item("name", 0, Rarity.COMMON);
    }

    @Test
    void testConstructor() {
        assertEquals(15, entity.getHealth());
        assertEquals(4, entity.getAttack());
        assertEquals(1, entity.getDefense());
        assertEquals(2, entity.getSpeed());
        assertEquals(5, entity.getLoot());
        assertEquals(0, entity.getInventory().size());
        assertEquals(0, entity.getX());
        assertEquals(0, entity.getY());
        assertEquals(0, entity.getDX());
        assertEquals(0, entity.getDY());
    }

    @Test
    void testSetHealth() {
        entity.setHealth(0);
        assertEquals(0, entity.getHealth());
    }

    @Test
    void testTakeDamage() {
        entity.takeDamage(1);
        assertEquals(14, entity.getHealth());
        entity.takeDamage(100);
        assertEquals(0, entity.getHealth());
    }

    @Test
    void testUpdate() {
        entity.setDX(1);
        entity.setDY(1);
        entity.update();
        assertEquals(1, entity.getX());
        assertEquals(1, entity.getY());
        entity.setDX(-3);
        entity.setDY(-3);
        entity.update();
        assertEquals(entity.getX(), 0);
        assertEquals(entity.getY(), 0);
        entity.setX(2000);
        entity.setY(2000);
        entity.update();
        assertEquals(GameState.WIDTH - entity.getImage().getWidth(null), entity.getX());
        assertEquals(GameState.HEIGHT - entity.getImage().getHeight(null), entity.getY());
        entity2.setX(2000);
        entity2.setY(2000);
        entity2.update();
        assertEquals(GameState.WIDTH, entity2.getX());
        assertEquals(GameState.HEIGHT, entity2.getY());
    }

    @Test
    void testAddToInventory() {
        entity.addToInventory(item);
        assertEquals(1, entity.getInventory().size());
    }

    @Test
    void testAddLoot() {
        entity.addLoot(1);
        assertEquals(6, entity.getLoot());
    }

    @Test
    void testJson() {
        entity.addToInventory(item);
        JSONObject jsEntity = entity.toJson();
        JSONArray jsArr = jsEntity.getJSONArray("inventory");
        assertEquals(1, jsArr.length());
        assertEquals(15, jsEntity.getDouble("health"));
        assertEquals(5, jsEntity.getInt("loot"));
    }

    
}
