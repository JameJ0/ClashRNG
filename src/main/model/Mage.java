package model;

import org.json.JSONObject;

// Represents a playable character with low health but extremely high attack
public class Mage extends Entity {

    // EFFECTS: creates a Mage with specific stats for Mage
    public Mage() {
        super();
        health = 10;
        speed = 3;
        defense = 0;
        attack = 15;
        mana = 100;
    }

    @Override
    public String toString() {
        return "Mage";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "Mage");
        return json;
    }

    public String getType() {
        return "Mage";
    }
}
