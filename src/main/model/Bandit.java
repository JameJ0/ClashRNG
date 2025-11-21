package model;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// represents a playable character with high speed and low defense
public class Bandit extends Player {
    private static Image sprite;

    // EFFECTS: creates a Bandit with specific stats for Bandit. Also loads the
    // sprite for the bandit, if not already loaded.
    public Bandit(String name) {
        super(name, 15.0, 10.0, 1.0, 6, 50.0);
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File("./res/images/bandit.png"));
                sprite = sprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                System.out.println("Error loading bandit image: " + e.getMessage());
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
