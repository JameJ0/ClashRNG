package model;

import java.awt.Image;

import org.json.JSONObject;

// represents a playable character with high speed and low defense
public class Bandit extends Player {

    // EFFECTS: creates a Bandit with specific stats for Bandit. Also loads the
    // sprite for the bandit, if not already loaded.
    public Bandit(String name, Image sprite) {
        super(name, 15.0, 10.0, 1.0, 6, 50.0, sprite);
    }

    // EFFECTS: returns the class' name
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
