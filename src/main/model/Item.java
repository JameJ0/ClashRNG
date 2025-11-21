package model;

import java.awt.Image;

import org.json.JSONObject;

import persistence.Writable;

// Representation of an item in the game, which can be placed in an inventory
public class Item implements Writable {
    private int weight;
    private String name;
    private Rarity rarity;
    private int posX;
    private int posY;

    private Image sprite;

    // REQUIRES: weight > 0
    // EFFECTS: creates a Item with a name, weight, and rarity.
    public Item(String name, int weight, Rarity rarity, Image sprite) {
        this.weight = weight;
        this.name = name;
        this.rarity = rarity;
        this.sprite = sprite;
    }

    // EFFECTS: get the value of an item based on rarity
    public int getValue() {
        switch (rarity) {
            case COMMON:
                return 1;
            case UNCOMMON:
                return 5;
            case RARE:
                return 10;
            case EPIC:
                return 50;
            default:
                return 100;
        }
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
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

    public Image getImage() {
        return sprite;
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
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
