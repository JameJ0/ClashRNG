package ui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import javax.swing.*;

import persistence.JsonReader;
import persistence.JsonWriter;

import model.exceptions.WrongEntityTypeException;
import model.*;

// The panel in which the game is rendered
public class GamePanel extends JPanel{

    private static final String JSON_STORE = "./data/gamestate.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private List<Enemy> enemies; // We need to hold the list of bad guys
    private Random rand;
    private GameState gs;
    private Player player;
    private Image bg;

    public GamePanel() {
        gs = new GameState();
        setPreferredSize(new Dimension(1600, 900));
        setBackground(Color.black);
        
        // SETUP PERSISTENCE
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        rand = new Random();

        // INITIALIZE GAME
        initGame(); // Let's clean up the constructor by moving setup to a helper method
        
        // ... existing image loading ...
        loadImages();
        
        // ... existing inputs ...
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyHandler());
    }

    // NEW HELPER: Handles starting a fresh game
    private void initGame() {
        enemies = new ArrayList<>();
        rand = new Random();
        
        // Create Player
        player = new Knight("Visual Hero");
        player.setX(100);
        player.setY(100);
        gs.setPlayer(player);

        // Create a Test Enemy (Ported from generateEnemies)
        Enemy goblin = new Goblin();
        goblin.setX(400);
        goblin.setY(300);
        enemies.add(goblin);
        
        gs.setEnemies(enemies);
    }

    public void loadImages() {
        try {
            bg = ImageIO.read(new File("./res/images/bg.jpg"));
        } catch (IOException e) {
            System.out.println("The path is wrong");
        }
    }

    // clears the screen
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
            drawGame(g);
        }

    public void drawGame(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(),getHeight(),this);
        drawPlayer(g, player);
        drawLOE(g, enemies);
    }


    public void drawLOE(Graphics g, List<Enemy> loe){
        for(Enemy e:loe){
            drawEnemy(g, e);
        }
    }

    public void drawEnemy(Graphics g, Enemy e){
        g.drawImage(e.toImage(), e.getX(), e.getY(), this);
    }

    public void drawPlayer(Graphics g, Player p){
        g.drawImage(p.toImage(), p.getX(), p.getY(), this);
    }   


    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int input = e.getKeyCode();
            int speed = (int)player.getSpeed();

            // Map keys to movement
            if (input == KeyEvent.VK_W || input == KeyEvent.VK_UP) {
                player.move(0, -speed); // Move UP (decrease Y)
            } else if (input == KeyEvent.VK_S || input == KeyEvent.VK_DOWN) {
                player.move(0, speed);  // Move DOWN (increase Y)
            } else if (input == KeyEvent.VK_A || input == KeyEvent.VK_LEFT) {
                player.move(-speed, 0); // Move LEFT
            } else if (input == KeyEvent.VK_D || input == KeyEvent.VK_RIGHT) {
                player.move(speed, 0);  // Move RIGHT
            }else if (input == KeyEvent.VK_O) {
                saveGameState();
            } else if (input == KeyEvent.VK_I) { // Added 'L' for quick loading
                loadGameState();
            }

            // Debug print to console to prove it's working
            System.out.println("Player moved to: " + player.getX() + ", " + player.getY());

            // IMPORTANT: Tell the panel to redraw itself now that coordinates changed
            repaint(); 
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the game to file
    private void saveGameState() {
        try {
            // Ensure the game state is up to date with the panel's local variables
            gs.setEnemies(enemies); 
            gs.setPlayer(player);
            
            jsonWriter.open();
            jsonWriter.write(gs);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private void loadGameState() {
        try {
            gs = jsonReader.read();
            
            // Update local variables from the loaded state
            player = gs.getPlayer();
            enemies = new ArrayList<>(gs.getEnemies());
            
            System.out.println("Loaded previous game from " + JSON_STORE);
            repaint(); // IMPORTANT: Redraw the screen with the new positions!
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (WrongEntityTypeException e) {
            System.out.println("Entity type mismatch");
        }
    }
    
}
