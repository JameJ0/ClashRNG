package model;

import java.awt.Image;
import java.awt.Image;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represents a moving entity in the game
public abstract class Entity implements Writable {

    private double health;
    private double attack;
    private double defense;
    private int speed;
    private int loot;

    private int posX;
    private int posY;
    private int dx;
    private int dy;

    private List<Item> inventory;

    // EFFECTS: Creates an entity with an empty inventory and specified stats
    public Entity(int loot, double hp, double atk, double def, int spd) {
        health = hp;
        attack = atk;
        defense = def;
        speed = spd;
        this.loot = loot;
        inventory = new ArrayList<>();
        posX = 0;
        posY = 0;
        dx = 0;
        dy = 0;
    }

    // MODIFIES: this
    // EFFECTS: move the Entity based on its speed in x and y
    public void update() {
        posX += dx;
        posY += dy;
        handleBoundaries();
    }

    // MODIFIES: this
    // EFFECTS: makes sure the Entity stays in the window
    public void handleBoundaries() {
        Image sprite = getImage();
        int windowWidth = GameState.WIDTH;
        int windowHeight = GameState.HEIGHT;
        if (sprite != null) {
            windowWidth -= sprite.getWidth(null);
            windowHeight -= sprite.getHeight(null);
        }
        if (posX < 0) {
            posX = 0;
        } else if (posX > windowWidth) {
            posX = windowWidth;
        }
        if (posY < 0) {
            posY = 0;
        } else if (posY > windowHeight) {
            posY = windowHeight;
        }
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

    // MODIFIES: this
    // EFFECTS: move the entity by dx and dy
    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
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

    public int getSpeed() {
        return speed;
    }

    public int getLoot() {
        return loot;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }
    
    public void setHealth(double health) {
        this.health = health;
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

    public void setDY(int dy) {
        this.dy = dy;
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

    // EFFECTS: returns the sprite of the entity
    public abstract Image getImage();

}
