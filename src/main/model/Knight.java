package model;

import org.json.JSONObject;

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

    @Override
    public String toString() {
        return "Knight";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "Knight");
        return json;
    }

    public String getType() {
        return "Knight";
    }
}
