package ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import model.*;

public class ConsoleGame {

    private Scanner sc;
    private ArrayList<Entity> enemies;
    private boolean gameOver;
    private Random r;
    private Entity player;

    public ConsoleGame() {
        init();
        roleSelect();
        printStats(player);
        System.out.println("Are you ready to start? (enter any text to continue)\n");
        sc.next();
        System.out.println("\nLevel 1-1 (console demo level)");
        generateEnemies();
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
        r = new Random();
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

    public void roleSelect() {
        boolean picked = false;
        System.out.println("\n\n\n\n\n\n\nWelcome to the game!");
        System.out.println("Please select your class (type the corresponding letter in the paranthesis):");
        System.out.println("\nKnight(k)  Mage(m)  Bandit(b)\n");
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
        int randNum = r.nextInt(5) + 1;
        for (int i = 0; i < randNum; i++) {
            enemies.add(new Goblin());
        }
        System.out.println("There are " + enemies.size() + " enemie(s) in this room.");
    }

    public void fight(Entity enemy) {
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {
            boolean picked = false;

            System.out.println("\nYour HP: " + player.getHealth());
            System.out.println("Eenmy HP: " + enemy.getHealth());
            System.out.println("\nWhat will you do?");
            System.out.println("\nfight(f)  stats(s)  view inventory(i)\n");
            while (!picked) {
                String input = sc.next();
                switch (input) {
                    case "f":
                        double playerTrueDamage = player.getAttack() - enemy.getDefense();
                        double enemyTrueDamage = enemy.getAttack() - player.getDefense();
                        enemy.setHealth(enemy.getHealth() - playerTrueDamage);
                        player.setHealth(player.getHealth() - enemyTrueDamage);
                        System.out.println("\nThe enemie has taken " + playerTrueDamage + " damage.");
                        System.out.println("\nThe player has taken " + enemyTrueDamage + " damage.");
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
        if (player.getHealth() < 0) {
            gameOver = true;
        } else {
            System.out.println("\nYou've defeated " + enemy + "!");
            player.addLoot(enemy.getLoot());

        }
    }

    public void printInventory(Entity e) {
        System.out.println("\nYou have " + player.getLoot() + " coins.");
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
            fight(currentEnemy);
            enemies.remove(0);
        }

    }

}
