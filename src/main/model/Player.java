package model;

import org.json.JSONObject;

import java.awt.Image;

// represents a player in the game
public abstract class Player extends Entity {
    private String name;
    private double mana;

    // EFFECTS: creates a player with a name and specified stats
    public Player(String name, double hp, double atk, double def, int spd, double mp, Image sprite) {
        super(0, hp, atk, def, spd, sprite);
        mana = mp;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getMana() {
        return mana;
    }

    // REQUIRES: 0 <= mana < max mana of the character (different depending on the
    // character)
    // MODIFIES: this
    // EFFECTS: set the player's mana to a specific number
    public void setMana(int mana) {
        this.mana = mana;
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", name);
        json.put("mana", mana);
        return json;
    }
    
}
