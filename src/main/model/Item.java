package model;

// Representation of an item in the game, which can be placed in an inventory
public class Item {
    int weight;
    String name;

    public Item(String name, int weight) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
