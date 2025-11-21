package ui;

import java.awt.*;

import java.io.File;
import java.io.IOException;

import java.util.List;

import javax.imageio.ImageIO;


import javax.swing.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import model.exceptions.WrongEntityTypeException;
import model.*;

// The panel in which the game is rendered
public class GamePanel extends JPanel {
    private GameState gs;
    private Image bg;

    public GamePanel(GameState gs) {
        this.gs = gs;
        setPreferredSize(new Dimension(GameState.WIDTH, GameState.HEIGHT));
        setBackground(Color.black);
        loadImages();
    }

    public void loadImages() {
        try {
            bg = ImageIO.read(new File("./res/images/bg.jpg"));
            bg = ImageIO.read(new File("./res/images/bg.jpg"));
        } catch (IOException e) {
            System.out.println("The path is wrong");
        }
    }

    // MODIFIES: g
    // EFFECTS: clears the screen and draw the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // MODIFIES: g
    // EFFECTS: draw the background and entities on the panel
    public void drawGame(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        drawPlayer(g, gs.getPlayer());
        drawEnemies(g, gs.getEnemies());
    }


    // MODIFIES: g
    // EFFECTS: a helper to draw the enemies on g
    public void drawEnemies(Graphics g, List<Enemy> loe) {
        for (Enemy e : loe ) {
            drawEnemy(g, e);
        }
    }

    // MODIFIES: g
    // EFFECTS: draw an enemy on g
    public void drawEnemy(Graphics g, Enemy e) {
        if (e != null && e.getImage() != null) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), this);
        }
    }

    // MODIFIES: g
    // EFFECTS: draw the palyer on g
    public void drawPlayer(Graphics g, Player p) {
        if (p != null && p.getImage() != null) {
            g.drawImage(p.getImage(), p.getX(), p.getY(), this);
        }
    }

}
