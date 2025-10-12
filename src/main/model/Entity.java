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

    public Entity() {
        inventory = new ArrayList<>();
        loot = 0;
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

    public double getMana() {
        return mana;
    }

    public double getSpeed() {
        return speed;
    }

    public int getLoot() {
        return loot;
    }

    public void addLoot(int n) {
        loot += n;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
