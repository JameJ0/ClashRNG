package model;

import org.json.JSONObject;

public class Player extends Entity {
    private String name;
    private double mana;

    public Player(String name, double hp, double atk, double def, double spd, double mp) {
        super(0, hp, atk, def, spd);
        mana = mp;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("name", name);
        json.put("mana", mana);
        return json;
    }

}
