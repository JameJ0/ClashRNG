package model;

public class Goblin extends Entity{

    public Goblin() {
        super();
        health = 20;
        speed = 5;
        defense = 1;
        attack = 5;
        mana = 0;
    }

    public String toString(){
        return "Goblin";
    }
}
