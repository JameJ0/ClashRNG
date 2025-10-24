package model;

import org.json.*;

import persistence.Writable;

import java.net.http.WebSocket;
import java.util.*;

public class GameState implements Writable{
    private Entity player;
    private List<Entity> enemies;

    public GameState() {
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

    public void removeEnemy(Entity enemy) {
        enemies.remove(enemy);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        return json;
    }

    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Entity enemy : enemies) {
            jsonArray.put(enemy.toJson());
        }
        return jsonArray;
    }
}
