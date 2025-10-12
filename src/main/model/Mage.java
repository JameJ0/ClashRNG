package model;

public class Mage extends Entity {

    public Mage(){
        super();
        health = 10;
        speed = 3;
        defense = 0;
        attack = 15;
        mana = 100;
    }
    
    public String toString(){
        return "Mage";
    }
}
