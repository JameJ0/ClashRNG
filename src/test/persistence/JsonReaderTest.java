package persistence;

import model.*;
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
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderNewGame.json");
        try {
            GameState gs = reader.read();
            assertEquals(0, gs.getEnemies().size());
            assertNull(gs.getPlayer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            GameState gs = reader.read();
            Entity player = gs.getPlayer();
            assertEquals(20, player.getHealth());
            List<Entity> enemies = gs.getEnemies();
            assertEquals(2, enemies.size());
            assertEquals(5, enemies.get(0).getLoot());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}