package model;

import org.json.JSONObject;

// Represents an goblin type enemy
public class Goblin extends Enemy {

    // EFFECTS: creates a Goblin with specific stats for a goblin
    public Goblin() {
        super(5, 15, 4, 1, 2);
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
