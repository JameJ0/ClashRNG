package ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// static assets so any class can access them anywhere
public class Assets {
    public static Image knightSprite;
    public static Image goblinSprite;
    public static Image mageSprite;
    public static Image banditSprite;
    public static Image bg;
    public static Image saveIcon;
    // I know the checkstyle doesnt like the public fields, but this is useful 
    // to load the image once and have it accessable everywher in the project

    // Load images globally
    public static void load() {
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
        } catch (IOException e) {
            System.out.println("Could not load images.");
        }
    }

}