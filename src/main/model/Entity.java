package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a moving  entity in the game
public class Entity implements Writable {
    private double health;
    private double attack;
    private double defense;
    private double speed;
    private int loot;

    private List<Item> inventory;

    // EFFECTS: Creates an entity with an empty inventory and specified stats
    public Entity(int loot, double hp, double atk, double def, double spd) {
        health = hp;
        attack = atk;
        defense = def;
        speed = spd;
        this.loot = loot;
        inventory = new ArrayList<>();
    }

    // REQUIRES: loot > 0
    // MODIFIES: this
    // EFFECTS: adds loot to the entity
    public void addLoot(int loot) {
        this.loot += loot;
    }

    // MODIFIES: this
    // EFFECTS: add an item to this entity's inventory
    public void addToInventory(Item i) {
        inventory.add(i);
    }

    // MODIFIES: this
    // EFFECTS: subtract health by incoming damage. if health is below zero, set it
    // to zero
    public void takeDamage(double damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getSpeed() {
        return speed;
    }

    public int getLoot() {
        return loot;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventory", inventoryToJson());
        json.put("health", health);
        json.put("loot", loot);
        return json;
    }

    // EFFECTS: a helper to turn the inventory to a JSONArray
    public JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Item item : inventory) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

}
