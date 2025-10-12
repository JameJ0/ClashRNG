package model;

public class Bandit extends Entity {

    public Bandit(){
        super();
        health = 15;
        speed = 5;
        defense = 1;
        attack = 10;
        mana = 50;
    }
    
    public String toString(){
        return "Bandit";
    }
}
