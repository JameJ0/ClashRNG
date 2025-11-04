package persistence;

import model.*;
import model.exceptions.TypeDoesNotExistException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads game from JSON data stored in file
// adapted from JsonSerializationDemo
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
        if (jsonObject.has("player") && !jsonObject.isNull("player")) {
            try {
                gs.setPlayer(parsePlayer(jsonObject.getJSONObject("player")));
            } catch (TypeDoesNotExistException e) {
                System.out.println("This is not a player");
            }
        }
        gs.setEnemies(parseEnemies(jsonObject.getJSONArray("enemies")));
        return gs;
    }

    private Player parsePlayer(JSONObject jsonObject) throws TypeDoesNotExistException {
        Player player;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Knight":
                player = new Knight(jsonObject.getString("name"));
                break;
            case "Mage":
                player = new Mage(jsonObject.getString("name"));
                break;
            case "Bandit":
                player = new Bandit(jsonObject.getString("name"));
                break;
            default:
                throw new TypeDoesNotExistException("The type " + type + " does not exist.");
        }
        List<Item> inventory = parseInventory(jsonObject.getJSONArray("inventory"));
        for (Item i : inventory) {
            player.addToInventory(i);
        }
        player.addLoot(jsonObject.getInt("loot"));
        return player;
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

    // EFFECTS: parses and instantiate an entity based on its type
    private Enemy parseEnemy(JSONObject jsonObject) throws TypeDoesNotExistException {
        Enemy enemy;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Goblin":
                enemy = new Goblin();
                break;
            default:
                throw new TypeDoesNotExistException("The type " + type + " does not exist.");
        }
        List<Item> inventory = parseInventory(jsonObject.getJSONArray("inventory"));
        for (Item i : inventory) {
            enemy.addToInventory(i);
        }
        return enemy;
    }

    // parses a jsonArray as a List of Entity
    private List<Enemy> parseEnemies(JSONArray jsonArray) {
        List<Enemy> enemies = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonEntity = (JSONObject) obj;
            try {
                enemies.add(parseEnemy(jsonEntity));
            } catch (TypeDoesNotExistException e) {
                System.out.println("The type " + jsonEntity.getString("type") + " not exist");
            }
        }
        return enemies;
    }
}
