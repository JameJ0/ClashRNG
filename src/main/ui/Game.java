package ui;

import javax.swing.*;

import java.awt.event.*;

import model.GameState;

// opens a window to the game and tells GameState if a key is pressed and when it repaints
public class Game extends JFrame {
    private static final int INTERVAL = 3; // TODO: 16 for 60FPS

    private GamePanel gp;
    private GameState gs;
    private Timer t;

    // EFFECTS: initializes the game mechanics (timer and keyListener), opens a
    // window and adds the panel to the window
    public Game() {
        super("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gs = new GameState();
        gp = new GamePanel(gs);

        add(gp);
        addKeyListener(new KeyHandler());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addTimer();
    }

    // EFFECTS: tells the gamestate that a key has been pressed/released
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            gs.keyPressed(e.getKeyCode());
        }
        @Override
        public void keyReleased(KeyEvent e) {
            gs.keyReleased(e.getKeyCode());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a timer that refreshes per INTERVAL and updates the game
    private void addTimer() {
        t = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gs.update();
                gp.repaint();
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        new Game();
    }
}
