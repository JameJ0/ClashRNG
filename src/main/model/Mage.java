package model;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// Represents a mage playable character with low health but extremely high attack
public class Mage extends Player {
    private static Image sprite;

    // EFFECTS: creates a Mage with specific stats for Mage, and loat its sprite
    public Mage(String name) {
        super(name, 10.0, 6.0, 3.0, 2, 100.0);
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File("./res/images/witch.png"));
                sprite = sprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                System.out.println("Error loading mage image: " + e.getMessage());
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

    // EFFECTS: returns the mage's sprite
    @Override
    public Image getImage() {
        return sprite;
    }
}
