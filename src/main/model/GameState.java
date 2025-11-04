package model;

import org.json.*;

import persistence.Writable;

import java.util.*;

//represents the state of the game (list of enemies, the player)
public class GameState implements Writable {
    private Player player;
    private List<Enemy> enemies;

    // EFFECTS: creates a new game with an empty enemies list
    public GameState() {
        player = null;
        enemies = new ArrayList<>();
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        return json;
    }

    // EFFECTS: returns items in your inventory as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Enemy enemy : enemies) {
            jsonArray.put(enemy.toJson());
        }
        return jsonArray;
    }
}
