package model;

import org.json.JSONObject;

// Represents an easy enemy entity  with random stats
public class Goblin extends Enemy {

    // EFFECTS: creates a Goblin with specific stats for Goblin
    public Goblin() {
        super(5, 15, 4, 1, 2);
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
