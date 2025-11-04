package model;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

public class TestItem {

    Item item1;
    Item item2;
    Item item3;
    Item item4;
    Item item5;

    @BeforeEach
    void runBefore() {
        item1 = new Item("Item", 1, "Common");
        item2 = new Item("Item", 2, "Uncommon");
        item3 = new Item("Item", 3, "Rare");
        item4 = new Item("Item", 4, "Epic");
        item5 = new Item("Item", 5, "Legendary");
    }

    @Test
    void testConstructor() {
        assertTrue(item1.getName().equals("Item"));
        assertEquals(1, item1.getWeight());
        assertTrue(item1.getRarity().equals("Common"));
    }

    @Test
    void testToString() {
        assertTrue(item1.toString().equals("Common Item"));
        assertTrue(item2.toString().equals("Uncommon Item"));
        assertTrue(item3.toString().equals("Rare Item"));
        assertTrue(item4.toString().equals("Epic Item"));
        assertTrue(item5.toString().equals("Legendary Item"));
    }

    @Test
    void testJson() {
        JSONObject jsItem = item1.toJson();
        assertEquals("Item", jsItem.getString("name"));
        assertEquals(1, jsItem.getInt("weight"));
        assertEquals("Common", jsItem.getString("rarity"));
    }
}
