package model;

public class Knight extends Entity {

    public Knight(){
        super();
        health = 100;
        speed = 2;
        defense = 20;
        attack = 10;
        mana = 50;
    }
    
    public String toString(){
        return "Knight";
    }
}
