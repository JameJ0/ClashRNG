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
public class GameFrame extends JFrame {
    private static final int INTERVAL = 3;

    private GamePanel gp;
    private GameState gs;
    private Timer timer;

    private static final String JSON_STORE = "./data/gamestate.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes the game mechanics (timer and keyListener), opens a
    // window and adds the panel to the window
    public GameFrame() {
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
        setupWindowListener();
        addTimer();
    }

    // EFFECTS: initializes the window listener to print the event log on close
    private void setupWindowListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        });
    }

    // EFFECTS: prints the whole log when the game closes
    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
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
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (WrongEntityTypeException e) {
            System.out.println("Entity type mismatch");
        }
    }

    public static void main(String[] args) {
        new GameFrame();
        // new ConsoleGame(); // Make the txt file java, then uncomment this line for ConsoleGame
    }
}
