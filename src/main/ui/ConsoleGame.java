// package ui;

// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.util.*;

// import javax.swing.JFrame;

// import model.*;
// import model.exceptions.WrongEntityTypeException;

// import persistence.JsonReader;
// import persistence.JsonWriter;

// // The essence of the game, all presented in the terminal.
// public class ConsoleGame extends JFrame {

//     private Scanner sc;
//     private List<Enemy> enemies;
//     private boolean gameOver;
//     private Random rand;
//     private Player player;
//     private String input;
//     private String name;

//     private static final String JSON_STORE = "./data/gamestate.json";
//     private JsonWriter jsonWriter;
//     private JsonReader jsonReader;

//     private GamePanel gp;
//     private GameState gameState;

//     // EFFECTS: creates an instance of the game console ui application
//     public ConsoleGame() throws FileNotFoundException {
//         super("test");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setUndecorated(false);
//         init();
//         initGameState();
//         runGame();
//     }

//     // MODIFIES: this
//     // EFFECTS: initializes the application with the starting values
//     public void init() {
//         gp = new GamePanel(gameState);
//         sc = new Scanner(System.in);
//         enemies = new ArrayList<>();
//         add(gp);
//         setVisible(true);
//         gameOver = false;
//         rand = new Random();
//         gameState = new GameState();
//         jsonWriter = new JsonWriter(JSON_STORE);
//         jsonReader = new JsonReader(JSON_STORE);
//     }

//     // EFFECTS: waits for the user to enter something, acting as a pause between
//     // large chunks of texts
//     public void pause() {
//         System.out.println("Take a minute to read this, then enter any text to continue.\n");
//         sc.next();
//     }

//     // MODIFIES: this
//     // EFFECTS: prints ome information about the mechanics of the game
//     public void gameIntro() {
//         System.out.println("\n                     Level 1-1 (console demo level)");
//         System.out.println("-------------------------------------------------------------------------------------");
//         System.out.println(
//              "In this level, there will be anywhere from 2 to 6 goblin-type enemies, which you fight sequentially.");
//         System.out.println("Fight through the enemies, and perhaps even earn loot and drops along the way.");
//         System.out.println(
//                 "Be careful. once your health drops to 0, you lose. You will not keep your items in your next run.");
//         System.out.println("Good luck " + name + ", and have fun!!\n");
//         System.out.println(
//                 "(This is just an oversimplified and modified version of my vision of the game, for Phase 1.)");
//         pause();
//     }

//     // EFFECTS: prints the player's stats
//     public void printPlayerStats() {
//         System.out.println("\nHere are your stats:");
//         System.out.println("------------------------------");
//         System.out.println("Attack = " + player.getAttack());
//         System.out.println("Defense = " + player.getDefense());
//         System.out.println("Speed = " + player.getSpeed());
//         System.out.println("Mana = " + player.getMana());
//         System.out.println("Health = " + player.getHealth() + "\n");
//     }

//     // MODIFIES: this
//     // EFFECTS: asks the user if they want to load a prev game. let the name and
//     // role select if not.
//     public void initGameState() {
//         System.out.println("\n\n\n\n\n\n\nWelcome to the game!");
//         System.out.println("\nWould you like to load the previous game? Y/N\n");
//         input = sc.next();
//         boolean picked = false;
//         while (!picked) {
//             switch (input.toLowerCase()) {
//                 case "y":
//                     loadGameState();
//                     picked = true;
//                     break;
//                 case "n":
//                     nameSelect();
//                     roleSelect();
//                     generateEnemies();
//                     printPlayerStats();
//                     gameIntro();
//                     picked = true;
//                     break;
//                 default:
//                     System.out.println("Please pick from the available options");
//                     break;
//             }
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: asks the user for their name and store it in the field
//     public void nameSelect() {
//         System.out.println("\nWhat is your name, adventurer?\n");
//         name = sc.next();
//     }

//     // MODIFIES: this
//     // EFFECTS: asks the user to choose a playable character
//     @SuppressWarnings("methodlength")
//     public void roleSelect() {
//         System.out.println("\nPlease select your class (type the corresponding letter in the paranthesis):");
//         System.out.println("\nKnight(k)  Mage(m)  Bandit(b)\n");
//         boolean picked = false;
//         while (!picked) {
//             input = sc.next();
//             switch (input.toLowerCase()) {
//                 case "k":
//                     player = new Knight(name);
//                     picked = true;
//                     break;
//                 case "m":
//                     player = new Mage(name);
//                     picked = true;
//                     break;
//                 case "b":
//                     player = new Bandit(name);
//                     picked = true;
//                     break;
//                 default:
//                     System.out.println("\nPlease choose from the available options.\n");
//                     break;
//             }
//         }
//         System.out.println("\nYou chose the " + player + " class!\n");
//     }

//     // MODIFIES: this
//     // EFFECTS: generate a random int between 2 and 6, then add that number of
//     // goblins to the enemies arraylist
//     public void generateEnemies() {
//         int randNum = rand.nextInt(5) + 2;
//         for (int i = 0; i < randNum; i++) {
//             Enemy goblin = new Goblin();
//             goblin.generateDrop(rand.nextDouble() * 100);
//             enemies.add(goblin);
//         }
//         System.out.println("There are " + enemies.size() + " enemie(s) in this room.");
//     }

//     // EFFECTS: print's the health of both players, then asks for an input
//     public void printBattleStatus(Enemy enemy) {
//         System.out.println("\nYour HP: " + player.getHealth());
//         System.out.println("Enemy HP: " + enemy.getHealth());
//         System.out.println("\nWhat will you do?");
//         System.out.println("\nfight(f)  info(i)  view inventory(v) save(s) quit(q)\n");
//     }

//     // MODIFIES: this
//     // EFFECTS: during enemy encounter take in user input, then do something based
//     // on that input.
//     @SuppressWarnings("methodlength")
//     public void battle(Enemy enemy) {
//         while (player.getHealth() > 0 && enemy.getHealth() > 0) {
//             boolean picked = false;
//             while (!picked) {
//                 printBattleStatus(enemy);
//                 String input = sc.next();
//                 switch (input.toLowerCase()) {
//                     case "f":
//                         calculateDamage(enemy);
//                         picked = true;
//                         break;
//                     case "i":
//                         printPlayerStats();
//                         break;
//                     case "v":
//                         printInventory();
//                         break;
//                     case "s":
//                         saveGameState();
//                         break;
//                     case "q":
//                         System.out.println("\nquitting game...\n");
//                         System.exit(0);
//                     default:
//                         System.out.println("\nPlease choose from the available options.\n");
//                         break;
//                 }
//             }
//         }
//         handleResults(enemy);
//     }

//     // MODIFIES: this
//     // EFFECTS: handles what should happen after defeating an enemy
//     public void handleResults(Enemy enemy) {
//         if (!gameOver) {
//             System.out.println("\nYou've defeated " + enemy + "!");
//             player.addLoot(enemy.getLoot());
//             double rng = 100 * rand.nextDouble();
//             if (rng < 50) {
//                 Item drop = enemy.generateDrop(rng);
//                 player.getInventory().add(drop);
//                 System.out.println("You picked up a " + drop + "!");
//             } else {
//                 System.out.println("The enemy didn't drop anything!");
//             }
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: calculate the damage to an enemy, based on your damage and the
//     // enemy's defense. if the player's hp goes below 0, the game ends
//     public void calculateDamage(Enemy enemy) {
//         double playerTrueDamage = player.getAttack() - enemy.getDefense();
//         double enemyTrueDamage = enemy.getAttack() - player.getDefense();
//         enemy.takeDamage(playerTrueDamage);
//         player.takeDamage(enemyTrueDamage);
//         System.out.println("\nThe enemy took " + playerTrueDamage + " damage.");
//         System.out.println("The player took " + enemyTrueDamage + " damage.");
//         if (player.getHealth() <= 0) {
//             gameOver = true;
//         }
//     }

//     // EFFECTS: prints the inventory and wealth of the player
//     public void printInventory() {
//         System.out.println("\nYou have " + player.getLoot() + " coins.\n");
//         if (player.getInventory().isEmpty()) {
//             System.out.println("You have nothing in your inventory...\n");
//         } else {
//             System.out.println("Here's what's in your inventory:");
//             for (Item i : player.getInventory()) {
//                 System.out.println(i);
//             }
//             System.out.println();
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: handles the game while player is in a level/room
//     public void runGame() {
//         while (!enemies.isEmpty() && !gameOver) {
//             Enemy currentEnemy = enemies.get(0);
//             System.out.println("\nYou've encountered a " + currentEnemy + "!");
//             battle(currentEnemy);
//             enemies.remove(0);
//         }
//         if (gameOver) {
//             System.out.println("\nYou lost!\n");
//         } else {
//             System.out.println("\nYou won!\n");
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: saves the game to file
//     private void saveGameState() {
//         gameState.setEnemies(enemies);
//         gameState.setPlayer(player);
//         try {
//             jsonWriter.open();
//             jsonWriter.write(gameState);
//             jsonWriter.close();
//             System.out.println("\nSaved game to " + JSON_STORE);
//         } catch (FileNotFoundException e) {
//             System.out.println("\nUnable to write to file: " + JSON_STORE);
//         }
//     }

//     // MODIFIES: this
//     // EFFECTS: loads game from file
//     private void loadGameState() {
//         try {
//             gameState = jsonReader.read();
//             player = gameState.getPlayer();
//             enemies = new ArrayList<>(gameState.getEnemies());
//             System.out.println("\nLoaded previous game from " + JSON_STORE);
//         } catch (IOException e) {
//             System.out.println("\nUnable to read from file: " + JSON_STORE);
//         } catch (WrongEntityTypeException e) {
//             System.out.println("\nentity type mismatch");sd
//         }
//     }

// }
