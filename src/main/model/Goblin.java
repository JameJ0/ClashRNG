package model;

import java.awt.Image;

import org.json.JSONObject;

// Represents an goblin type enemy
public class Goblin extends Enemy {

    // EFFECTS: creates a Goblin with specific stats for a goblin, and loads its
    // sprite
    public Goblin(Image sprite) {
        super(5, 15, 4, 1, 2, sprite);
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
