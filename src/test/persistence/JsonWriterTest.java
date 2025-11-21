package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// adapted from JsonSerializationDemo
class JsonWriterTest {

    List<Enemy> enemies;
    Enemy goblin1;
    Enemy goblin2;

    @BeforeEach
    void runBefore() {
        enemies = new ArrayList<>();
        goblin1 = new Goblin(null);
        goblin2 = new Goblin(null);
        enemies.add(goblin1);
        enemies.add(goblin2);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            GameState gs = new GameState();
            Player p = new Knight("name", null);
            gs.setPlayer(p);
            gs.setEnemies(enemies);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGameState.json");
            writer.open();
            writer.write(gs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGameState.json");
            gs = reader.read();

            p = gs.getPlayer();
            assertNotNull(p);
            assertEquals("Knight", p.toString());
            assertEquals(20, p.getHealth());

            enemies = gs.getEnemies();
            assertEquals(2, enemies.size());
            assertEquals(5, enemies.get(0).getLoot());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}