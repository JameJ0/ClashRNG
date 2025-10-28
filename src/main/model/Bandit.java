package model;

import org.json.JSONObject;

// Represents a playable character with high speed and low defense
public class Bandit extends Entity {

    // EFFECTS: creates a Bandit with specific stats for Bandit
    public Bandit() {
        super();
        health = 15;
        speed = 5;
        defense = 1;
        attack = 10;
        mana = 50;
    }

    @Override
    public String toString() {
        return "Bandit";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "Bandit");
        return json;
    }

    public String getType() {
        return "Bandit";
    }
}
