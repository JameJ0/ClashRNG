package model;

import org.json.JSONObject;

public class Player extends Entity {
    private String name;
    private double mana;
    private int loot;

    public Player(String name, double hp, double atk, double def, double spd, double mp) {
        super(hp, atk, def, spd);
        mana = mp;
        this.name = name;
        loot = 0;
    }

    public String getName() {
        return name;
    }

    public double getMana() {
        return mana;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", name);
        json.put("loot", loot);
        return json;
    }

    public int getLoot () {
        return loot;
    }

    public void addLoot (int loot) {
        
    }
}
