// adapted from JsonSerializationDemo
package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest{

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
    void testWriterEmptyWorkroom() {
        try {
            GameState gs = new GameState();
            JsonWriter writer = new JsonWriter("./data/testWriterNewGame.json");
            writer.open();
            writer.write(gs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewGame.json");
            gs = reader.read();

            assertNull(gs.getPlayer());
            assertEquals(0, gs.getEnemies().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            GameState gs = new GameState();
            Entity p = new Knight();
            gs.setPlayer(p);
            Entity e1 = new Goblin();
            Entity e2 = new Goblin();
            gs.addEnemy(e1);
            gs.addEnemy(e2);

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

            List<Entity> enemies = gs.getEnemies();
            assertEquals(2, enemies.size());
            assertEquals(5, enemies.get(0).getLoot());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}