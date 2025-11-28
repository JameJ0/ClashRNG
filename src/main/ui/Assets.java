package ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import model.Rarity;

// static assets so any class can access them anywhere (Singleton)
public class Assets {
    private Image knightSprite;
    private Image goblinSprite;
    private Image mageSprite;
    private Image banditSprite;
    private Image bg;
    private Image saveIcon;
    private Image commonItem;
    private Image uncommonItem;
    private Image rareItem;
    private Image epicItem;
    private Image legendaryItem;

    private static Assets instance = new Assets();

    // EFFECTS: contructs the assets library once
    private Assets() {
        load();
    }

    // EFFECTS: returns an instance of the library
    public static Assets getInstance() {
        return instance;
    }

    // MODIFIES: this
    // EFFECTS: load images during the instantiation of the object
    @SuppressWarnings("methodlength")
    private void load() {
        try {
            knightSprite = ImageIO.read(new File("./res/images/knight.png"));
            knightSprite = knightSprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            goblinSprite = ImageIO.read(new File("./res/images/goblin.png"));
            goblinSprite = goblinSprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            mageSprite = ImageIO.read(new File("./res/images/witch.png"));
            mageSprite = mageSprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            banditSprite = ImageIO.read(new File("./res/images/bandit.png"));
            banditSprite = banditSprite.getScaledInstance(50, -1, Image.SCALE_SMOOTH);

            bg = ImageIO.read(new File("./res/images/bg.jpg"));

            saveIcon = ImageIO.read(new File("./res/images/saveIcon.png"));
            saveIcon = saveIcon.getScaledInstance(100, -1, Image.SCALE_SMOOTH);

            commonItem = ImageIO.read(new File("./res/images/commonItem.png"));
            commonItem = commonItem.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            uncommonItem = ImageIO.read(new File("./res/images/uncommonItem.png"));
            uncommonItem = uncommonItem.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            rareItem = ImageIO.read(new File("./res/images/rareItem.png"));
            rareItem = rareItem.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            epicItem = ImageIO.read(new File("./res/images/epicItem.png"));
            epicItem = epicItem.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
            legendaryItem = ImageIO.read(new File("./res/images/legendaryItem.png"));
            legendaryItem = legendaryItem.getScaledInstance(50, -1, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.out.println("Could not load images.");
        }
    }

    // EFFECTS: returns an image of an item based on rarity
    public Image getItemImage(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return commonItem;
            case UNCOMMON:
                return uncommonItem;
            case RARE:
                return rareItem;
            case EPIC:
                return epicItem;
            default:
                return legendaryItem;
        }
    }

    public Image getKnightSprite() {
        return knightSprite;
    }

    public Image getGoblinSprite() {
        return goblinSprite;
    }

    public Image getMageSprite() {
        return mageSprite;
    }

    public Image getBanditSprite() {
        return banditSprite;
    }

    public Image getBg() {
        return bg;
    }

    public Image getSaveIcon() {
        return saveIcon;
    }

}