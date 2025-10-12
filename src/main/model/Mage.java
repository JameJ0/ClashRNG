package model;

public class Mage extends Entity {

    public Mage(){
        super();
        health = 50;
        speed = 3;
        defense = 5;
        attack = 15;
        mana = 100;
    }
    
    public String toString(){
        return "Mage";
    }
}
