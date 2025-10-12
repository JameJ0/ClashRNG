package ui;

import java.util.*;
import model.*;

public class ConsoleGame {

    private Scanner sc;
    private ArrayList<Entity> enemies;
    private boolean gameOver;
    private Random rand;
    private Entity player;

    public ConsoleGame() {
        init();
        welcome();
        roleSelect();
        printStats(player);
        pause();
        preLevel();
        runGame();
        if (gameOver) {
            System.out.println("\nYou lost!\n");
        } else {
            System.out.println("\nYou won!\n");
        }
    }

    public void init() {
        sc = new Scanner(System.in);
        enemies = new ArrayList<>();
        gameOver = false;
        rand = new Random();
    }

    public void pause() {
        System.out.println("Take a minute to read this, then enter any text to continue.");
        sc.next();
    }

    public void preLevel() {
        generateEnemies();
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

    public void printStats(Entity e) {
        System.out.println();
        System.out.println("Attack = " + e.getAttack());
        System.out.println("Defense = " + e.getDefense());
        System.out.println("Speed = " + e.getSpeed());
        System.out.println("Mana = " + e.getMana());
        System.out.println("Health = " + e.getHealth());
        System.out.println();

    }

    public void welcome() {
        System.out.println("\n\n\n\n\n\n\nWelcome to the game!");
        System.out.println("Please select your class (type the corresponding letter in the paranthesis):");
        System.out.println("\nKnight(k)  Mage(m)  Bandit(b)\n");
    }

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

    public void generateEnemies() {
        int randNum = rand.nextInt(5) + 2;
        for (int i = 0; i < randNum; i++) {
            enemies.add(new Goblin());
        }
        System.out.println("There are " + enemies.size() + " enemie(s) in this room.");
    }

    public void printBattleStatus(Entity enemy) {
        System.out.println("\nYour HP: " + player.getHealth());
        System.out.println("Enemy HP: " + enemy.getHealth());
        System.out.println("\nWhat will you do?");
        System.out.println("\nfight(f)  stats(s)  view inventory(i)\n");
    }

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
                    case "s":
                        printStats(player);
                        break;
                    case "i":
                        printInventory(player);
                        break;
                    default:
                        System.out.println("\nPlease choose from the available options.\n");
                        break;
                }
            }
        }
        handleResults(enemy);
    }

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

    public void calculateDamage(Entity enemy) {
        double playerTrueDamage = player.getAttack() - enemy.getDefense();
        double enemyTrueDamage = enemy.getAttack() - player.getDefense();
        enemy.setHealth(enemy.getHealth() - playerTrueDamage);
        player.setHealth(player.getHealth() - enemyTrueDamage);
        System.out.println("\nThe enemy took " + playerTrueDamage + " damage.");
        System.out.println("\nThe player took " + enemyTrueDamage + " damage.");
    }

    public Item generateDrop(double n) {
        String rarity;
        if (n < 1) {
            rarity = "Legendary";
        } else if (n < 3) {
            rarity = "Epic";
        } else if (n < 10) {
            rarity = "Rare";
        } else if (n < 25) {
            rarity = "Uncommon";
        } else {
            rarity = "Common";
        }
        return new Item("Item", 0, rarity);
    }

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

    public void runGame() {
        while (!enemies.isEmpty() && !gameOver) {
            Entity currentEnemy = enemies.get(0);
            System.out.println("\nYou've encountered a " + currentEnemy + "!");
            battle(currentEnemy);
            enemies.remove(0);
        }

    }

}
