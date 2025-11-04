package model;

import org.json.*;

import persistence.Writable;

import java.util.*;

// represents the state of the game as a list of enemies and the player
public class GameState implements Writable {
    private Player player;
    private List<Enemy> enemies;

    // EFFECTS: creates a new game
    public GameState() {
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
