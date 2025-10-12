package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestWallet {

    Wallet w;

    @BeforeEach
    void runBefore() {
        w = new Wallet();
    }

    @Test
    void testConstructor() {
        assertEquals(0, w.getCoins());
        assertEquals(5, w.getWeight());
        assertTrue(w.getName().equals("Wallet"));
    }

    @Test
    void testAdd() {
        w.add(10);
        assertEquals(10, w.getCoins());
        w.add(5);
        assertEquals(15, w.getCoins());
    }
}
