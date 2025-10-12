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
        printStats();
        while (!gameOver) {
            runGame();
        }
    }

    public void init() {
        sc = new Scanner(System.in);
        enemies = new ArrayList<>();
        gameOver = false;
        r = new Random();
    }

    public void roleSelect() {
        System.out.println("Welcome to the game!");
        boolean picked = false;
        System.out.println("Please select your class:");
        System.out.println("Knight(k)  Mage(m)  Bandit(b)");
        System.out.println("(type the corresponding letter in paranthesis)");
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
                    System.out.println("Please choose from the available options.");
                    break;
            }
        }
        System.out.println("You chose the "+ player +" class!");
    }

    public void printStats(){
        System.out.println("-----------------------------");
        System.out.println(player);
        
    }

    public void runGame() {
        String input = sc.nextLine();
    }

}
