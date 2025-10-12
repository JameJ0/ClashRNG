package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestItem {

    Item i;

    @BeforeEach
    void runBefore() {
        i = new Item("Item", 1);
    }

    @Test
    void testConstructor() {
        assertTrue(i.getName().equals("Item"));
        assertEquals(1, i.getWeight());
    }
}
