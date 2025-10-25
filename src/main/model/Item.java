package model;

import org.json.JSONObject;
import persistence.Writable;

// Representation of an item in the game, which can be placed in an inventory
public class Item implements Writable{
    private int weight;
    private String name;
    private String rarity;

    // REQUIRES: weight > 0, rarity is one of (hopefully we learn enums soon):
    // "Common", "Uncommon", "Rare", "Epic", "Legendary"
    // EFFECTS: creates a Item with a name, weight, and rarity.
    public Item(String name, int weight, String rarity) {
        this.weight = weight;
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getRarity() {
        return rarity;
    }

    public String toString() {
        return getRarity() + " " + getName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("rarity", rarity);
        return json;
    }
}
