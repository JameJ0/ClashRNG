package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// Represents a knight playable character with high health and defense, but low attack
public class Knight extends Player {

    // EFFECTS: creates a knight with the player's name
    public Knight(String name) {
        super(name, 20.0, 3.0, 5.0, 2.0, 50.0);
        try {
            sprite = ImageIO.read(new File("./res/images/knight.png"));
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
