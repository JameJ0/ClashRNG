package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// Represents an goblin type enemy
public class Goblin extends Enemy {

    // EFFECTS: creates a Goblin with specific stats for a goblin
    public Goblin() {
        super(5, 15, 4, 1, 2);
        try {
            sprite = ImageIO.read(new File("./res/images/goblin.png"));
            sprite = sprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println("The path is wrong");
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("type", toString());
        return json;
    }

} 
