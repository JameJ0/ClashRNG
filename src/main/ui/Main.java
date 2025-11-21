package ui;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        // new ConsoleGame();
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the new visual panel
        GamePanel panel = new GamePanel();
        window.add(panel);
        window.pack();
        
        window.setLocationRelativeTo(null); // Centers window
        window.setVisible(true);
    }
}
