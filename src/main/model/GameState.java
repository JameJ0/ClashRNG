package model;

import org.json.*;

import persistence.Writable;

import java.util.*;

//represents the state of the game (list of enemies, the player)
public class GameState implements Writable {
    private Entity player;
    private List<Entity> enemies;

    // EFFECTS: creates a new game with an empty enemies list
    public GameState() {
        player = null;
        enemies = new ArrayList<>();
    }

    public Entity getPlayer() {
        return player;
    }

    public List<Entity> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }

    public void setEnemies(List<Entity> enemies) {
        this.enemies = enemies;
    }

    public void addEnemy(Entity enemy) {
        enemies.add(enemy);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        if (player != null) {
            json.put("player", player.toJson());
        } else {
            json.put("player", JSONObject.NULL);
        }
        json.put("enemies", enemiesToJson());
        return json;
    }

    // EFFECTS: returns items in your inventory as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Entity enemy : enemies) {
            jsonArray.put(enemy.toJson());
        }
        return jsonArray;
    }
}
