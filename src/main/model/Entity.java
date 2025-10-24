package model;

// Represents an entity in the game, like the player or an enemy, for example.
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class Entity implements Writable{
    protected double health;
    protected double attack;
    protected double defense;
    protected double mana;
    protected double speed;
    protected int loot;

    protected List<Item> inventory;

    // EFFECTS: Creates an entity with an inventory and no coins (loot)
    public Entity() {
        inventory = new ArrayList<>();
        loot = 0;
    }

    // EFFECTS: returns the of health the entity
    public double getHealth() {
        return health;
    }

    // EFFECTS: returns the of attack the entity
    public double getAttack() {
        return attack;
    }

    // EFFECTS: returns the defense of the entity
    public double getDefense() {
        return defense;
    }

    // EFFECTS: returns the mana of the entity
    public double getMana() {
        return mana;
    }

    // EFFECTS: returns the speed of the entity
    public double getSpeed() {
        return speed;
    }

    // EFFECTS: returns the loot of the entity
    public int getLoot() {
        return loot;
    }

    // REQUIRES: n > 0
    // MODIFIES: this
    // EFFECTS: add n to loot
    public void addLoot(int n) {
        loot += n;
    }

    // MODIFIES: this
    // EFFECTS: set the health to a specifc number
    public void setHealth(double health) {
        this.health = health;
    }

    // MODIFIES: this
    // EFFECTS: set the mana to a specifc number
    public void setMana(double mana) {
        this.mana = mana;
    }

    // MODIFIES: this
    // EFFECTS: set the speed to a specifc number
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // MODIFIES: this
    // EFFECTS: set the loot to a specifc number
    public void setLoot(int loot) {
        this.loot = loot;
    }

    // MODIFIES: this
    // EFFECTS: set the defense to a specifc number
    public void setDefense(double defense) {
        this.defense = defense;
    }

    // MODIFIES: this
    // EFFECTS: set the attack to a specifc number
    public void setAttack(double attack) {
        this.attack = attack;
    }

    // MODIFIES: this
    // EFFECTS: set the inventory of an entity
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
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
        json.put("mana", mana);
        json.put("speed", speed);
        json.put("loot", loot);
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
