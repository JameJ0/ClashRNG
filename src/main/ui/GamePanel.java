package ui;

import java.awt.*;

import java.util.List;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.*;

// The panel in which the game is rendered
public class GamePanel extends JPanel {
    private GameState gs;
    private Game game; // so we can save from the button

    public GamePanel(GameState gs, Game game) {
        this.gs = gs;
        this.game = game;

        setPreferredSize(new Dimension(GameState.WIDTH, GameState.HEIGHT));
        setBackground(Color.black);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        int iconW = Assets.saveIcon.getWidth(null);
        int iconH = Assets.saveIcon.getHeight(null);

        int btnX = getWidth() - iconW;
        int btnY = 0;

        if (mouseX >= btnX && mouseX <= btnX + iconW 
                && mouseY >= btnY && mouseY <= btnY + iconH) {
            System.out.println("Game Saved");
            game.saveGame();
        }
    }

    public void setGameState(GameState load) {
        gs = load;
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
        g.drawImage(Assets.bg, 0, 0, getWidth(), getHeight(), this);
        drawUI(g);
        drawPlayer(g, gs.getPlayer());
        drawEnemies(g, gs.getEnemies());
    }

    // MODIFIES: g
    // EFFECTS: a helper to draw the enemies on g
    public void drawEnemies(Graphics g, List<Enemy> loe) {
        for (Enemy e : loe) {
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

    public void drawUI(Graphics g) {
        if (Assets.saveIcon != null) {
            g.drawImage(Assets.saveIcon, getWidth() - Assets.saveIcon.getWidth(null), 0, null);
        }
    }

}
