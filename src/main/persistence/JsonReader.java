package persistence;

import model.*;
import model.exceptions.WrongEntityTypeException;
import ui.Assets;

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
    // throws WrongEntityTypeException if the types on file are wrong for the object
    public GameState read() throws IOException, WrongEntityTypeException {
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
    private GameState parseGameState(JSONObject jsonObject) throws WrongEntityTypeException {
        GameState gs = new GameState();
        gs.setPlayer(parsePlayer(jsonObject.getJSONObject("player")));
        gs.setEnemies(parseEnemies(jsonObject.getJSONArray("enemies")));
        return gs;
    }

    // EFFECTS: parses player from JSONObject and returns it
    private Player parsePlayer(JSONObject jsonObject) throws WrongEntityTypeException {
        Player player;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Knight":
                player = new Knight(jsonObject.getString("name"), Assets.knightSprite);
                break;
            case "Mage":
                player = new Mage(jsonObject.getString("name"), Assets.mageSprite);
                break;
            case "Bandit":
                player = new Bandit(jsonObject.getString("name"), Assets.banditSprite);
                break;
            default:
                throw new WrongEntityTypeException("The type " + type + " does not exist.");
        }
        List<Item> inventory = parseInventory(jsonObject.getJSONArray("inventory"));
        for (Item i : inventory) {
            player.addToInventory(i);
        }
        player.addLoot(jsonObject.getInt("loot"));
        player.setHealth(jsonObject.getDouble("health"));
        player.setMana(jsonObject.getInt("mana"));
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
        String rarityString = jsonObject.getString("rarity");
        Rarity rarity = Rarity.valueOf(rarityString.toUpperCase());
        return new Item(name, weight, rarity);
    }

    // EFFECTS: parses and instantiate an entity based on its type
    // throws WrongEntityTypeException if the types on file are wrong for the object
    private Enemy parseEnemy(JSONObject jsonObject) throws WrongEntityTypeException {
        Enemy enemy;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Goblin":
                enemy = new Goblin(Assets.goblinSprite);
                break;
            default:
                throw new WrongEntityTypeException("The type " + type + " does not exist.");
        }
        List<Item> inventory = parseInventory(jsonObject.getJSONArray("inventory"));
        for (Item i : inventory) {
            enemy.addToInventory(i);
        }
        enemy.setHealth(jsonObject.getDouble("health"));
        return enemy;
    }

    // EFFECTS: parses a jsonArray as a List of Entity
    // throws WrongEntityTypeException if the types on file are wrong for the object
    private List<Enemy> parseEnemies(JSONArray jsonArray) throws WrongEntityTypeException {
        List<Enemy> enemies = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonEntity = (JSONObject) obj;
            enemies.add(parseEnemy(jsonEntity));
        }
        return enemies;
    }
    
}
