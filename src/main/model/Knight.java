package model;

import java.awt.Image;

import org.json.JSONObject;

// Represents a knight playable character with high health and defense, but low attack
public class Knight extends Player {

    // EFFECTS: creates a knight with the player's name, and load its sprite
    public Knight(String name, Image sprite) {
        super(name, 20.0, 3.0, 5.0, 2, 50.0, sprite);
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
