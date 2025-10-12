package model;

public class Knight extends Entity {

    public Knight(){
        super();
        health = 20;
        speed = 2;
        defense = 2;
        attack = 5;
        mana = 50;
    }
    
    public String toString(){
        return "Knight";
    }
}
