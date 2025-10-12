package model;

// Represents an easy enemy entity  with random stats
public class Goblin extends Entity {

    public Goblin() {
        super();
        health = 15;
        speed = 2;
        defense = 1;
        attack = 4;
        mana = 0;
        
    }

    public String toString() {
        return "Goblin";
    }

}
