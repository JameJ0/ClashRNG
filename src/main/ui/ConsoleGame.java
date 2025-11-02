package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// The essence of the game, all presented in the terminal.
public class ConsoleGame {

    private Scanner sc;
    private List<Entity> enemies;
    private boolean gameOver;
    private Random rand;
    private Entity player;

    private static final String JSON_STORE = "./data/gamestate.json";
    private GameState gameState;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean newGame;

    // EFFECTS: creates an instance of the game console ui application
    public ConsoleGame() throws FileNotFoundException {
        gameState = new GameState();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
        printWelcome();
        if (newGame) {
            roleSelect();
            generateEnemies();
        } else {
            player = gameState.getPlayer();
            enemies = new ArrayList<>(gameState.getEnemies());
        }
        gameState.setPlayer(player);
        printStats(player);
        pause();
        preLevel();
        gameState.setEnemies(enemies);

        runGame();
    }

    // MODIFIES: this
    // EFFECTS: initializes the application with the starting values
    public void init() {
        sc = new Scanner(System.in);
        enemies = new ArrayList<>();
        gameOver = false;
        rand = new Random();
        newGame = true;
    }

    // EFFECTS: waits for the user to enter something, acting as a pause between
    // large chunks of texts
    public void pause() {
        System.out.println("Take a minute to read this, then enter any text to continue.");
        sc.next();
    }

    // MODIFIES: this
    // EFFECTS: randomly generates 2-6 enemies, and prints ome information about the
    // mechanics of the game
    public void preLevel() {
        System.out.println("\n        Level 1-1 (console demo level)");
        System.out.println("-----------------------------------------------");
        System.out.println(
                "In this level, there will be anywhere from 2 to 6 goblin-type enemies, which you fight sequentially.");
        System.out.println("Fight through the enemies, and perhaps even earn loot and drops along the way.");
        System.out.println(
                "Be careful. once your health drops to 0, you lose. You will not keep your items in your next run.");
        System.out.println("Good luck, have fun!!\n");
        System.out.println(
                "(This is just an oversimplified and modified version of my vision of the game, for Phase 1.)");
        pause();
    }

    // EFFECTS: prints an entity's stats
    public void printStats(Entity e) {
        System.out.println();
        System.out.println("Attack = " + e.getAttack());
        System.out.println("Defense = " + e.getDefense());
        System.out.println("Speed = " + e.getSpeed());
        System.out.println("Mana = " + e.getMana());
        System.out.println("Health = " + e.getHealth());
        System.out.println();

    }

    // EFFECTS: prints the main menu lines when the application initializes
    public void printWelcome() {
        System.out.println("\n\n\n\n\n\n\nWelcome to the game!");
        System.out.println("\nWould you like to load the previous game? Y/N");
        String input = sc.next();
        if (input.equalsIgnoreCase("y")) {
            loadGameState();
            newGame = false;
        } else {
            System.out.println("Please select your class (type the corresponding letter in the paranthesis):");
            System.out.println("\nKnight(k)  Mage(m)  Bandit(b)\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: asks the user for an input and initialize a playable entity as the
    // player
    public void roleSelect() {
        boolean picked = false;
        while (!picked) {
            String input = sc.next();
            switch (input) {
                case "k":
                    player = new Knight();
                    picked = true;
                    break;
                case "m":
                    player = new Mage();
                    picked = true;
                    break;
                case "b":
                    player = new Bandit();
                    picked = true;
                    break;
                default:
                    System.out.println("\nPlease choose from the available options.\n");
                    break;
            }
        }
        System.out.println("\nYou chose the " + player + " class! Here are your stats:\n");
    }

    // MODIFIES: this
    // EFFECTS: generate a random int between 2 and 6, then add that number of
    // goblins to the enemies arraylist
    public void generateEnemies() {
        int randNum = rand.nextInt(5) + 2;
        for (int i = 0; i < randNum; i++) {
            enemies.add(new Goblin());
        }
        System.out.println("There are " + enemies.size() + " enemie(s) in this room.");
    }

    // EFFECTS: print's the health of both players, then asks for an input
    public void printBattleStatus(Entity enemy) {
        System.out.println("\nYour HP: " + player.getHealth());
        System.out.println("Enemy HP: " + enemy.getHealth());
        System.out.println("\nWhat will you do?");
        System.out.println("\nfight(f)  info(i)  view inventory(v) save(s) quit(q)\n");
    }

    // MODIFIES: this
    // EFFECTS: during enemy encounter take in user input, then do something based
    // on that input.
    public void battle(Entity enemy) {
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            boolean picked = false;
            printBattleStatus(enemy);
            while (!picked) {
                String input = sc.next();
                switch (input) {
                    case "f":
                        calculateDamage(enemy);
                        picked = true;
                        break;
                    case "i":
                        printStats(player);
                        break;
                    case "v":
                        printInventory(player);
                        break;
                    case "s":
                        saveGameState();
                        break;
                    case "q":
                        System.out.println("quitting game...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nPlease choose from the available options.\n");
                        break;
                }
            }
        }
        handleResults(enemy);
    }

    // MODIFIES: this
    // EFFECTS: handles what should happen after an encounter (whether player/enemy
    // has been defeated)
    public void handleResults(Entity enemy) {
        if (player.getHealth() < 0) {
            gameOver = true;
        } else {
            System.out.println("\nYou've defeated " + enemy + "!");
            player.addLoot(enemy.getLoot());
            double rng = 100 * Math.random();
            if (rng < 50) {
                Item drop = generateDrop(rng);
                player.getInventory().add(drop);
                System.out.println("You picked up a " + drop + "!");
            } else {
                System.out.println("The enemy didn't drop anything!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: calculate the damage to an enemy, based on your damage and the
    // enemy's defense
    public void calculateDamage(Entity enemy) {
        double playerTrueDamage = player.getAttack() - enemy.getDefense();
        double enemyTrueDamage = enemy.getAttack() - player.getDefense();
        enemy.takeDamage(enemy.getHealth() - playerTrueDamage);
        player.takeDamage(player.getHealth() - enemyTrueDamage);
        System.out.println("\nThe enemy took " + playerTrueDamage + " damage.");
        System.out.println("\nThe player took " + enemyTrueDamage + " damage.");
    }


    // EFFECTS: prints the inventory and wealth of an entity
    public void printInventory(Entity e) {
        System.out.println("\nYou have " + player.getLoot() + " coins.\n");
        if (e.getInventory().isEmpty()) {
            System.out.println("You have nothing in your inventory...\n");
        } else {
            System.out.println("Here's what's in your inventory:");
            for (Item i : e.getInventory()) {
                System.out.println(i);
            }
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the game while player is in a level
    public void runGame() {
        while (!enemies.isEmpty() && !gameOver) {
            Entity currentEnemy = enemies.get(0);
            System.out.println("\nYou've encountered a " + currentEnemy + "!");
            battle(currentEnemy);
            enemies.remove(0);
        }
        if (gameOver) {
            System.out.println("\nYou lost!\n");
        } else {
            System.out.println("\nYou won!\n");
        }
    }

    // EFFECTS: saves the game to file
    private void saveGameState() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameState);
            jsonWriter.close();
            System.out.println("Saved previous game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private void loadGameState() {
        try {
            gameState = jsonReader.read();
            System.out.println("Loaded previous game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
