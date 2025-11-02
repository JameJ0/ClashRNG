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

    private List<Item> inventory;

    // EFFECTS: Creates an entity with an inventory and no coins (loot)
    public Entity(double hp, double atk, double def, double spd) {
        health = hp;
        attack = atk;
        defense = def;
        speed = spd;
        inventory = new ArrayList<>();
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

    // EFFECTS: returns the list of items of the entity
    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("health", health);
        json.put("attack", attack);
        json.put("defense", defense);
        json.put("speed", speed);
        json.put("inventory", inventoryToJson());
        return json;
    }

    private JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Item item : inventory) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

}
