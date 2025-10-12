package model;

// Representation of an item in the game, which can be placed in an inventory
public class Item {
    private int weight;
    private String name;
    private String rarity;

    public Item(String name, int weight, String rarity) {
        this.weight = weight;
        this.name = name;
        this.rarity = rarity;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public String getRarity() {
        return rarity;
    }

    public String toString() {
        return getRarity() + " " + getName();
    }
}
