package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.*;

// The panel in which the game is rendered
public class GamePanel extends JPanel{
    private GameState gs;
    private BufferedImage butterfly;

    public GamePanel(GameState gs) {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.gray);
        loadImages();
        this.gs = gs;
    }

    public void loadImages() {
        try {
            butterfly = ImageIO.read(new File("/res/images/butterfly.jpg"));
        } catch (IOException e) {
            System.out.println("The path is wrong");
        }
    }

    public void drawGame(Graphics g) {
        drawPlayer(g);
    }

    public void drawPlayer(Graphics g) {
        g.drawImage(butterfly, 0, 0, this);
    }
    
}
