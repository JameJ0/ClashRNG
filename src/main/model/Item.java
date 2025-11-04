package model;

import org.json.JSONObject;

import persistence.Writable;

// Representation of an item in the game, which can be placed in an inventory
public class Item implements Writable {
    private int weight;
    private String name;
    private Rarity rarity;

    // REQUIRES: weight > 0
    // EFFECTS: creates a Item with a name, weight, and rarity.
    public Item(String name, int weight, Rarity rarity) {
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

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public String toString() {
        String rarityString = rarity.name();
        return rarityString.substring(0, 1) + rarityString.substring(1).toLowerCase() + " " + getName();
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weight", weight);
        json.put("rarity", rarity.name());
        return json;
    }
}
