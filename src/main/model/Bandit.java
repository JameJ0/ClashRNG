package model;

import org.json.JSONObject;

// Represents a playable character with high speed and low defense
public class Bandit extends Player {

    // EFFECTS: creates a Bandit with specific stats for Bandit
    public Bandit(String name) {
        super(name, 15.0, 10.0, 1.0, 6.0, 50.0);
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
