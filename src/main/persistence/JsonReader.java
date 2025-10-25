// modified from JsonSerializationDemo
package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads game from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameState read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameState(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses game from JSON object and returns it
    private GameState parseGameState(JSONObject jsonObject) {
        GameState gs = new GameState();
        Entity player = parseEntity(jsonObject.getJSONObject("player"));
        gs.setPlayer(player);
        List<Entity> enemies = parseEntities(jsonObject.getJSONArray("enemies"));
        gs.setEnemies(enemies);
        return gs;
    }

    // EFFECTS: parses an entity based on its type
    private Entity parseEntity(JSONObject jsonObject) {
        Entity entity;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Goblin":
                entity = new Goblin();
                break;
            case "Knight":
                entity = new Knight();
                break;
            case "Mage":
                entity = new Mage();
                break;
            case "Bandit":
                entity = new Bandit();
                break;
            default:
                entity = new Entity();
                break;
        }
        entity.setHealth(jsonObject.getDouble("health"));
        entity.setAttack(jsonObject.getDouble("attack"));
        entity.setDefense(jsonObject.getDouble("defense"));
        entity.setMana(jsonObject.getDouble("mana"));
        entity.setSpeed(jsonObject.getDouble("speed"));
        entity.setLoot(jsonObject.getInt("loot"));
        entity.setInventory(parseInventory(jsonObject.getJSONArray("inventory")));
        return entity;
    }

    // parses the items in a jsonArray as a List
    private List<Item> parseInventory(JSONArray jsonArray) {
        List<Item> inventory = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonItem = (JSONObject) obj;
            inventory.add(parseItem(jsonItem));
        }
        return inventory;
    }

    // parses a jsonObject as an Item
    private Item parseItem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        String rarity = jsonObject.getString("rarity");
        return new Item(name, weight, rarity);
    }

    //parses a jsonArray as a List of Entity
    private List<Entity> parseEntities(JSONArray jsonArray) {
        List<Entity> entities = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonEntity = (JSONObject) obj;
            entities.add(parseEntity(jsonEntity));
        }
        return entities;
    }
}
