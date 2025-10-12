package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestItem {

    Item i1, i2, i3, i4, i5;

    @BeforeEach
    void runBefore() {
        i1 = new Item("Item", 1, "Common");
        i2 = new Item("Item", 1, "Uncommon");
        i3 = new Item("Item", 1, "Rare");
        i4 = new Item("Item", 1, "Epic");
        i5 = new Item("Item", 1, "Legendary");
    }

    @Test
    void testConstructor() {
        assertTrue(i1.getName().equals("Item"));
        assertEquals(1, i1.getWeight());
        assertTrue(i1.getRarity().equals("Common"));
    }

    @Test
    void testToString() {
        assertTrue(i1.toString().equals("Common Item"));
        assertTrue(i2.toString().equals("Uncommon Item"));
        assertTrue(i3.toString().equals("Rare Item"));
        assertTrue(i4.toString().equals("Epic Item"));
        assertTrue(i5.toString().equals("Legendary Item"));
    }
}
