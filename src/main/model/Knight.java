package model;

// Represents a playable character with high health and defense, but low attack
public class Knight extends Entity {

    // EFFECTS: creates a Mage with specific stats for Mage
    public Knight() {
        super();
        health = 20;
        speed = 2;
        defense = 3;
        attack = 5;
        mana = 50;
    }

    public String toString() {
        return "Knight";
    }
}
