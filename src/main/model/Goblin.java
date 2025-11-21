package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;

// Represents an goblin type enemy
public class Goblin extends Enemy {
    private static Image sprite;

    // EFFECTS: creates a Goblin with specific stats for a goblin, and loads its
    // sprite
    public Goblin() {
        super(5, 15, 4, 1, 2);
        if (sprite == null) {
            try {
                sprite = ImageIO.read(new File("./res/images/goblin.png"));
                sprite = sprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                System.out.println("Error loading goblin image: " + e.getMessage());
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
