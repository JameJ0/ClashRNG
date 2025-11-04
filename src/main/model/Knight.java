package model;

import org.json.JSONObject;

// Represents a knight playable character with high health and defense, but low attack
public class Knight extends Player {

    // EFFECTS: creates a knight with the player's name
    public Knight(String name) {
        super(name, 20.0, 3.0, 5.0, 2.0, 50.0);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", toString());
        return json;
    }
}
