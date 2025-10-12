package model;

import java.util.ArrayList;

public class Player {
    protected double health;
    protected double attack;
    protected double defense;
    protected double mana;
    protected double speed;
    protected ArrayList<Item> inventory;
    protected String name;

    public Player(String name) {
        health = 100;
        inventory = new ArrayList<>();
    }
}
