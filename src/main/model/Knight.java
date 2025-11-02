package model;

import org.json.JSONObject;

// Represents a playable character with high health and defense, but low attack
public class Knight extends Player {

    // EFFECTS: creates a Mage with specific stats for Mage
    public Knight(String name) {
        super(name, 20.0, 3.0, 5.0, 2.0, 50.0);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", toString());
        return json;
    }
}
