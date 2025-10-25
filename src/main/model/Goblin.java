package model;

import org.json.JSONObject;

// Represents an easy enemy entity  with random stats
public class Goblin extends Entity {

    // EFFECTS: creates a Goblin with specific stats for Goblin
    public Goblin() {
        super();
        health = 15;
        speed = 2;
        defense = 1;
        attack = 4;
        mana = 0;
        loot = 5;
    }

    @Override
    public String toString() {
        return "Goblin";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", "Goblin");
        return json;
    }

    public String getType() {
        return "Goblin";
    }

}
