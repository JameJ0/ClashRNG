package model;

import java.awt.Image;

import org.json.JSONObject;

// Represents a mage playable character with low health but extremely high attack
public class Mage extends Player {

    // EFFECTS: creates a Mage with specific stats for Mage, and loat its sprite
    public Mage(String name, Image sprite) {
        super(name, 10.0, 6.0, 3.0, 2, 100.0, sprite);
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
