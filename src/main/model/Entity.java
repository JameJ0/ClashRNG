package model;

// Represents an entity in the game, like the player or an enemy, for example.
import java.util.ArrayList;

public class Entity {
    protected double health;
    protected double attack;
    protected double defense;
    protected double mana;
    protected double speed;
    protected int loot;

    protected ArrayList<Item> inventory;

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

    // EFFECTS: returns the list of items of the entity
    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
