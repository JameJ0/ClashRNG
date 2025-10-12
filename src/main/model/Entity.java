package model;

import java.util.ArrayList;

public class Entity {
    protected double health;
    protected double attack;
    protected double defense;
    protected double mana;
    protected double speed;
    protected ArrayList<Item> inventory;

    public Entity() {
        inventory = new ArrayList<>();
    }
}
