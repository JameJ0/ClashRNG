package model;

// Represents a playable character with high speed and low defense
public class Bandit extends Entity {

    public Bandit() {
        super();
        health = 15;
        speed = 5;
        defense = 1;
        attack = 10;
        mana = 50;
    }

    public String toString() {
        return "Bandit";
    }
}
