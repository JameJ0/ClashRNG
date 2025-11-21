package model;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// Represents a knight playable character with high health and defense, but low attack
public class Knight extends Player {
    private static Image sprite;

    // EFFECTS: creates a knight with the player's name, and load its sprite
    public Knight(String name) {
        super(name, 20.0, 3.0, 5.0, 20, 50.0); // TODO: changed speed to 2 after done
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File("./res/images/knight.png"));
                sprite = sprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                System.out.println("Error loading knight image: " + e.getMessage());
            }
        }
    }

    // EFFECTS: returns the class' name
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

    // EFFECTS: returns the bandit's sprite
    @Override
    public Image getImage() {
        return sprite;
    }
}
