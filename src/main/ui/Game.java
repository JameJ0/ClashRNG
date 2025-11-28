package ui;

import javax.swing.*;

import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.*;
import model.exceptions.WrongEntityTypeException;
import persistence.JsonReader;
import persistence.JsonWriter;

// opens a window to the game and tells GameState if a key is pressed and when it repaints
public class Game extends JFrame {
    private static final int INTERVAL = 3;

    private GamePanel gp;
    private GameState gs;
    private Timer timer;

    private static final String JSON_STORE = "./data/gamestate.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes the game mechanics (timer and keyListener), opens a
    // window and adds the panel to the window
    public Game() {
        super("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Assets.getInstance();
        gs = new GameState();
        gp = new GamePanel(gs, this);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        add(gp);
        addKeyListener(new KeyHandler());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        addTimer();
    }

    // MODIFIES: this
    // EFFECTS: Starts a new game instance.
    public void startNewGame() {
        gs = new GameState();
        gp.setGameState(gs);
        gs.setStatus(GameState.Status.PLAYING);
    }

    // MODIFIES: this
    // EFFECTS: load state from file
    public void loadAndStart() {
        loadGameState();
        gs.setStatus(GameState.Status.PLAYING);
    }

    // MODIFIES: this, gs
    // EFFECTS: removes the item at the given index from the player's inventory
    // and adds a calculate amount to their loot.
    public void sellItem(int index) {
        List<Item> inventory = gs.getPlayer().getInventory();
        Item itemToSell = inventory.get(index);
        int sellValue = itemToSell.getValue();

        gs.getPlayer().addLoot(sellValue);
        inventory.remove(index);
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
        timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                gs.update();
                gp.repaint();
            }
        });
        timer.start();
    }

    // MODIFIES: this
    // tells the gamestate to sell all common items to save time
    public void sellAllCommonItems() {
        gs.discardCommonItems();
    }

    // MODIFIES: this
    // EFFECTS: saves the game to file
    public void saveGame() {
        try {
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
    public void loadGameState() {
        try {
            gs = jsonReader.read();
            gp.setGameState(gs);
            System.out.println("Loaded previous game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (WrongEntityTypeException e) {
            System.out.println("Entity type mismatch");
        }
    }

    public static void main(String[] args) {
        new Game(); //Uncomment this for the GUI
        // honestly i can and will add some more to the game. i spent so much time
        // building the structure that there was no more time to implement fun features.
        // the hard part (sorting out the GUI and heirarchies) is done, now its just
        // minor tweaks and QOL stuff.
        // new ConsoleGame(); // Uncomment this line for ConsoleGame
    }
}
