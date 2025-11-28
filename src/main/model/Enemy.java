package model;

import java.awt.Image;

import ui.Assets;

// represents a general enemy in the game
public abstract class Enemy extends Entity {

    // EFFECTS: creates an enemy with specified stats
    public Enemy(int loot, double hp, double atk, double def, int spd, Image sprite) {
        super(loot, hp, atk, def, spd, sprite);
    }

    // REQUIRES: 0 < randNum < 50
    // EFFECTS: generate an Item based on a a rng parameter, then returns it
    public Item generateDrop(double randNum) {
        Rarity rarity;
        if (randNum < 1) {
            rarity = Rarity.LEGENDARY;
        } else if (randNum < 3) {
            rarity = Rarity.EPIC;
        } else if (randNum < 10) {
            rarity = Rarity.RARE;
        } else if (randNum < 20) {
            rarity = Rarity.UNCOMMON;
        } else {
            rarity = Rarity.COMMON;
        }
        return new Item("Item", 2, rarity, Assets.getInstance().getItemImage(rarity));
    }

}
