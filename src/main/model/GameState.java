package model;

import org.json.*;

import model.exceptions.WrongEntityTypeException;

import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.awt.event.KeyEvent;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;

// represents the state of the game as a list of enemies and the player. 
// Also handles key presses and all the updates
public class GameState implements Writable {
    private Player player;
    private List<Enemy> enemies;

    private static final String JSON_STORE = "./data/gamestate.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Random rand;

    // EFFECTS: creates a new game and initialize entites
    public GameState() {
        rand = new Random();
        enemies = new ArrayList<>();

        player = new Knight("Test");
        player.setX(100);
        player.setY(100);

        Enemy goblin = new Goblin();
        goblin.setX(400);
        goblin.setY(300);
        enemies.add(goblin);
    }

    // MODIFIES: this
    // changes the positions of the entites in the game
    public void update() {
        player.update();
        for (Enemy e : enemies) {
            e.update();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    // MODIFIES: this
    // EFFECTS: set the velocity of the player based in the key input
    public void keyPressed(int keyCode) {
        int speed = player.getSpeed();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            player.setDY(-speed);
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            player.setDY(speed);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            player.setDX(-speed);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            player.setDX(speed);
        } else if (keyCode == KeyEvent.VK_O) {
            saveGameState();
        } else if (keyCode == KeyEvent.VK_I) {
            loadGameState();
        }
    }

    // MODIFIES: this
    // EFFECTS: stops the player when the key is being released
    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            player.setDX(0);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            player.setDX(0);
        }
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        return json;
    }

    // EFFECTS: returns the enemies as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Enemy enemy : enemies) {
            jsonArray.put(enemy.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: saves the game to file
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private void loadGameState() {
        try {
            GameState load = jsonReader.read();
            player = load.getPlayer();
            enemies = new ArrayList<>(load.getEnemies());
            System.out.println("Loaded previous game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (WrongEntityTypeException e) {
            System.out.println("Entity type mismatch");
        }
    }
}
