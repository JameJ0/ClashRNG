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
                gs.setPlayer(parseEntity(jsonObject.getJSONObject("player")));
            } catch (TypeDoesNotExistException e) {
                System.out.println("This is not a player");
            }
        }
        gs.setEnemies(parseEntities(jsonObject.getJSONArray("enemies")));
        return gs;
    }

    // EFFECTS: parses and instantiate an entity based on its type
    private Entity parseEntity(JSONObject jsonObject) throws TypeDoesNotExistException {
        Entity entity;
        String type = jsonObject.getString("type");
        switch (type) {
            case "Goblin":
                entity = new Goblin();
                break;
            case "Knight":
                entity = new Knight(jsonObject.getString("name"));
                break;
            case "Mage":
                entity = new Mage(jsonObject.getString("name"));
                break;
            case "Bandit":
                entity = new Bandit(jsonObject.getString("name"));
                break;
            default:
                throw new TypeDoesNotExistException("The type " + type + " does not exist.");
        }
        parseInventoryToEntity(entity, jsonObject.getJSONArray("invenotory"));
        return entity;
    }

    // parses the items in a jsonArray as a List
    private void parseInventoryToEntity(Entity e, JSONArray jsonArray) {
        List<Item> inventory = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonItem = (JSONObject) obj;
            inventory.add(parseItem(jsonItem));
        }
        for (Item i : inventory) {
            e.addToInventory(i);
        }
    }

    // parses a jsonObject as an Item
    private Item parseItem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        String rarity = jsonObject.getString("rarity");
        return new Item(name, weight, rarity);
    }

    // parses a jsonArray as a List of Entity
    private List<Entity> parseEntities(JSONArray jsonArray) {
        List<Entity> entities = new ArrayList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonEntity = (JSONObject) obj;
            try {
                entities.add(parseEntity(jsonEntity));
            } catch (TypeDoesNotExistException e) {
                System.out.println("The type " + jsonEntity.getString("type") + " not exist");
            }
        }
        return entities;
    }
}
