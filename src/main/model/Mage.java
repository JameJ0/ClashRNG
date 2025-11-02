package model;

import org.json.JSONObject;

// Represents a playable character with low health but extremely high attack
public class Mage extends Player {

    // EFFECTS: creates a Mage with specific stats for Mage
    public Mage(String name) {
        super(name, 20.0, 5.0, 3.0, 2.0, 100.0);
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
