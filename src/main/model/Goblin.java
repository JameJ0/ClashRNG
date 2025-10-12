package model;

import java.util.Random;

// Represents an easy enemy entity  with random stats
public class Goblin extends Entity {

    Random r = new Random();

    public Goblin() {
        super();
        health = r.nextInt(10) + 15;
        speed = r.nextInt(2) + 3;
        defense = r.nextInt(2) + 1;
        attack = r.nextInt(3) + 4;
        mana = 0;
    }

    public String toString() {
        return "Goblin";
    }

}
