package model;

import java.util.ArrayList;

public class Entity {
    protected double health;
    protected double attack;
    protected double defense;
    protected double mana;
    protected double speed;
    protected ArrayList<Item> inventory;

    public Entity() {
        inventory = new ArrayList<>();
    }

    public void printStats(){
        System.out.println();
        System.out.println("Attack = " + getAttack());
        System.out.println("Defense = " + getDefense());
        System.out.println("Speed = " + getSpeed());
        System.out.println("Mana = " + getMana());
        System.out.println("Health = " + getHealth());
        System.out.println();

    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getMana() {
        return mana;
    }

    public double getSpeed() {
        return speed;
    }

    public void setHealth(double health){
        this.health = health;
    }
}
