package model;

// Representation of an item in the game, which can be placed in an inventory
public class Item {
    private int weight;
    private String name;
    private String rarity;

    // REQUIRES: weight > 0, rarity is one of (hopefully we learn enums soon):
    // "Common", "Uncommon", "Rare", "Epic", "Legendary"
    // EFFECTS: creates a Item with a name, weight, and rarity.
    public Item(String name, int weight, String rarity) {
        this.weight = weight;
        this.name = name;
        this.rarity = rarity;
    }

    // EFFECTS: returns the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: returns the weight of the item
    public int getWeight() {
        return weight;
    }

    // EFFECTS: returns the rarity of the item
    public String getRarity() {
        return rarity;
    }

    public String toString() {
        return getRarity() + " " + getName();
    }
}
