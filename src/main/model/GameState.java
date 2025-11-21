package model;

import org.json.*;

import persistence.Writable;
import ui.Assets;

import java.awt.event.KeyEvent;

import java.util.*;

// represents the state of the game as a list of enemies and the player. 
// Also handles key presses and all the updates
public class GameState implements Writable {
    private Player player;
    private List<Enemy> enemies;

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Random rand;

    // EFFECTS: creates a new game and initialize entites
    public GameState() {
        rand = new Random();
        enemies = new ArrayList<>();

        player = new Knight("Test", Assets.knightSprite);
        player.setX(100);
        player.setY(100);

        Enemy goblin = new Goblin(Assets.goblinSprite);
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
        if (keyCode == KeyEvent.VK_W) {
            player.setDY(-speed);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDY(speed);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setDX(-speed);
        }
        if (keyCode == KeyEvent.VK_D) {
            player.setDX(speed);
        }
    }

    // MODIFIES: this
    // EFFECTS: stops the player when the key is being released
    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setDX(0);
        }
        if (keyCode == KeyEvent.VK_D) {
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

}
