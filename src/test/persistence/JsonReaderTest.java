package persistence;

import model.*;
import model.exceptions.WrongEntityTypeException;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// adapted from JsonSerializationDemo
class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (WrongEntityTypeException e) {
            fail("should've thrown exception");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            GameState gs = reader.read();
            Player player = gs.getPlayer();
            assertEquals(10, player.getHealth());
            List<Enemy> enemies = gs.getEnemies();
            assertEquals(2, enemies.size());
            assertEquals(15, enemies.get(0).getHealth());
            assertEquals(10, enemies.get(1).getHealth());
            assertEquals(1, enemies.get(0).getInventory().size());
            List<Item> inventory = player.getInventory();
            assertEquals(1, inventory.size());
        } catch (Exception e) {
            fail("should've thrown exception");
        }
    }

    @Test
    void testReaderWrongPlayerType() {
        JsonReader reader = new JsonReader("./data/testReaderWrongPlayerType.json");
        try {
            reader.read();
            fail("should've thrown exception");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (WrongEntityTypeException e) {
            // Expected
        }
    }

    @Test
    void testReaderWrongEnemyType() {
        JsonReader reader = new JsonReader("./data/testReaderWrongEnemyType.json");
        try {
            reader.read();
            fail("should've thrown exception");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (WrongEntityTypeException e) {
            // Expected
        }
    }

    @Test
    void testReaderBanditGame() {
        JsonReader reader = new JsonReader("./data/testReaderBandit.json");
        try {
            GameState gs = reader.read();
            Player player = gs.getPlayer();
            assertEquals("Bandit", player.toString());
        } catch (Exception e) {
            fail("should've thrown exception");
        }
    }

    @Test
    void testReaderMageGame() {
        JsonReader reader = new JsonReader("./data/testReaderMage.json");
        try {
            GameState gs = reader.read();
            Player player = gs.getPlayer();
            assertEquals("Mage", player.toString());
        } catch (Exception e) {
            fail("should've thrown exception");
        }
    }
}