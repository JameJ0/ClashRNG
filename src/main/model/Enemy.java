package model;

import org.json.JSONObject;

public class Enemy extends Entity {

    public Enemy(int loot, double hp, double atk, double def, double spd) {
        super(loot, hp, atk, def, spd);
    }

    // EFFECTS: generate an Item based on a double which is an rng, and put into
    // inventory
    public Item generateDrop(double randNum) {
        String rarity;
        if (randNum < 1) {
            rarity = "Legendary";
        } else if (randNum < 3) {
            rarity = "Epic";
        } else if (randNum < 10) {
            rarity = "Rare";
        } else if (randNum < 20) {
            rarity = "Uncommon";
        } else {
            rarity = "Common";
        }
        return new Item("Item", 2, rarity);
    }
}
