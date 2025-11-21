package ui;

import java.awt.*;

import java.util.List;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.*;
import model.GameState.Status;

// The panel in which the game is rendered
public class GamePanel extends JPanel {
    private GameState gs;
    private Game game;

    private static final int INV_BAR_HEIGHT = 80;
    private static final int ITEM_PADDING = 40;
    private static final int INV_TEXT_OFFSET_Y = 15;

    private static final int SELL_ALL_BTN_X = 20;
    private static final int SELL_ALL_BTN_WIDTH = 100;
    private static final int SELL_ALL_BTN_HEIGHT = 40;

    private static final int MENU_BTN_W = 200;
    private static final int MENU_BTN_H = 50;
    private static final int MENU_BTN_X = GameState.WIDTH / 2 - MENU_BTN_W / 2;
    private static final int MENU_BTN_Y_START = 300;

    // EFFECTS: initiates the panel, and add the gamestate and the game controller
    public GamePanel(GameState gs, Game game) {
        this.gs = gs;
        this.game = game;

        setPreferredSize(new Dimension(GameState.WIDTH, GameState.HEIGHT));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: helper on the menu screen
    private void handleLoadingScreen(int mouseX, int mouseY) {
        if (gs.getStatus() == GameState.Status.MENU) {
            if (mouseX >= MENU_BTN_X && mouseX <= MENU_BTN_X + MENU_BTN_W 
                    && mouseY >= MENU_BTN_Y_START && mouseY <= MENU_BTN_Y_START + MENU_BTN_H) {
                game.startNewGame();
            } else if (mouseX >= MENU_BTN_X && mouseX <= MENU_BTN_X + MENU_BTN_W 
                    && mouseY >= MENU_BTN_Y_START + 70 && mouseY <= MENU_BTN_Y_START + 70 + MENU_BTN_H) {
                game.loadAndStart();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: helper on the save button
    private void handleSave(int mouseX, int mouseY) {
        int iconW = Assets.saveIcon.getWidth(null);
        int iconH = Assets.saveIcon.getHeight(null);

        int btnX = getWidth() - iconW;
        int btnY = 0;

        if (mouseX >= btnX && mouseX <= btnX + iconW
                && mouseY >= btnY && mouseY <= btnY + iconH) {
            System.out.println("Game Saved");
            game.saveGame();
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: helper to sell the item on click. works by getting the index of the
    // item based on the cursor's location
    private void handleSellOnClick(int mouseX, int mouseY) {
        int panelHeight = getHeight();
        if (mouseY >= panelHeight - INV_BAR_HEIGHT && mouseY <= panelHeight) {
            List<Item> inventory = gs.getPlayer().getInventory();
            int currentX = ITEM_PADDING;

            for (int index = 0; index < inventory.size(); index++) {
                Item item = inventory.get(index);
                Image sprite = item.getImage();
                int itemW = sprite.getWidth(null);

                if (mouseX >= currentX && mouseX <= currentX + itemW) {
                    game.sellItem(index);
                    repaint();
                    break;
                }

                currentX += itemW + ITEM_PADDING;

            }
        }
    }

    private void handleSellAll(int mouseX, int mouseY) {
        int sellbtnY = getHeight() - INV_BAR_HEIGHT - 10 - SELL_ALL_BTN_HEIGHT;
        if (mouseX >= SELL_ALL_BTN_X && mouseX <= SELL_ALL_BTN_X + SELL_ALL_BTN_WIDTH && mouseY >= sellbtnY
                && mouseY <= sellbtnY + SELL_ALL_BTN_HEIGHT) {
            game.sellAllCommonItems();
        }
    }

    // MODIFIES: this
    // EFFECTS: handle mouse input and tell the game what to do
    private void handleMouseClick(int mouseX, int mouseY) {

        handleLoadingScreen(mouseX, mouseY);

        handleSave(mouseX, mouseY);

        handleSellOnClick(mouseX, mouseY);

        handleSellAll(mouseX, mouseY);

    }

    // MODIFIES: g
    // EFFECTS: clears the screen and draw the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // MODIFIES: g
    // EFFECTS: draw the background, UI, player and entities on the panel
    // Draws the menu of the Status is MENU
    public void drawGame(Graphics g) {
        if (gs.getStatus() == Status.MENU) {
            drawMenu(g);
        } else {
            g.drawImage(Assets.bg, 0, 0, getWidth(), getHeight(), this);
            drawPlayer(g, gs.getPlayer());
            drawEnemies(g, gs.getEnemies());
            drawDroppedItems(g, gs.getDroppedItems());

            drawInventory(g);
            drawCoins(g);
            drawUI(g);
        }

    }

    // MODIFIES: g
    // EFFECTS: Draws the Main Menu screen with save/load buttons
    public void drawMenu(Graphics g) {
        setBackground(Color.black);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Game", MENU_BTN_X + 30, MENU_BTN_Y_START - 100);

        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawRect(MENU_BTN_X, MENU_BTN_Y_START, MENU_BTN_W, MENU_BTN_H);
        g.drawString("NEW GAME", MENU_BTN_X + 30, MENU_BTN_Y_START + 35);

        g.drawRect(MENU_BTN_X, MENU_BTN_Y_START + 70, MENU_BTN_W, MENU_BTN_H);
        g.drawString("LOAD GAME", MENU_BTN_X + 30, MENU_BTN_Y_START + 70 + 35);
    }

    // MODIFIES: g
    // EFFECTS: display your wealth in the corner
    public void drawCoins(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Coins: " + gs.getPlayer().getLoot(), 0, 20);
    }

    // MODIFIES: g
    // EFFECTS: draw your inventory on the bottom (like mc!)
    public void drawInventory(Graphics g) {
        List<Item> inventory = gs.getPlayer().getInventory();
        int panelHeight = getHeight();

        g.setColor(new Color(50, 50, 50, 200));
        g.fillRect(0, panelHeight - INV_BAR_HEIGHT, getWidth(), INV_BAR_HEIGHT);
        int currentX = ITEM_PADDING;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        for (Item item : inventory) {
            Image sprite = item.getImage();
            int itemW = sprite.getWidth(null);
            g.drawString(item.toString(), currentX - 5, panelHeight - INV_BAR_HEIGHT + INV_TEXT_OFFSET_Y);
            g.drawImage(sprite, currentX, panelHeight - INV_BAR_HEIGHT + INV_TEXT_OFFSET_Y + 5, this);
            currentX += itemW + ITEM_PADDING;
        }

    }

    // MODIFIES: g
    // EFFECTS: draw the items dropped by enemies when they die
    private void drawDroppedItems(Graphics g, List<Item> items) {
        for (Item i : items) {
            g.drawImage(i.getImage(), i.getX(), i.getY(), this);
        }
    }

    // MODIFIES: g
    // EFFECTS: a helper to draw the enemies on g
    private void drawEnemies(Graphics g, List<Enemy> loe) {
        for (Enemy e : loe) {
            drawEnemy(g, e);
        }
    }

    // MODIFIES: g
    // EFFECTS: draw an enemy on g
    private void drawEnemy(Graphics g, Enemy e) {
        if (e != null && e.getImage() != null) {
            g.drawImage(e.getImage(), e.getX(), e.getY(), this);
            drawHealthBar(g, e);
        }
    }

    // MODIFIES: g
    // EFFECTS: draw a health bar over an entity
    private void drawHealthBar(Graphics g, Entity e) {
        final int BAR_HEIGHT = 10;
        final int BAR_WIDTH = 50;
        final int BAR_OFFSET = 10;

        double hpRatio = e.getHealth() / e.getMaxHealth();
        int currentBarWidth = (int) (hpRatio * BAR_WIDTH);

        int spriteWidth = e.getImage().getWidth(null);
        int barX = e.getX() + (spriteWidth / 2) - (BAR_WIDTH / 2);
        int barY = e.getY() - BAR_HEIGHT - BAR_OFFSET;

        g.setColor(Color.BLACK);
        g.fillRect(barX, barY, BAR_WIDTH, BAR_HEIGHT);
        g.setColor(Color.RED);
        g.fillRect(barX, barY, currentBarWidth, BAR_HEIGHT);
    }

    // MODIFIES: g
    // EFFECTS: draw the player on g
    public void drawPlayer(Graphics g, Player p) {
        if (p != null && p.getImage() != null) {
            g.drawImage(p.getImage(), p.getX(), p.getY(), this);
            drawHealthBar(g, p);
        }
    }

    // MODIFIES: g
    // EFFECTS: draw the buttons in the UI (save icon, sell all button)
    public void drawUI(Graphics g) {
        if (Assets.saveIcon != null) {
            g.drawImage(Assets.saveIcon, getWidth() - Assets.saveIcon.getWidth(null), 0, null);
        }

        int btnY = getHeight() - INV_BAR_HEIGHT - 10 - SELL_ALL_BTN_HEIGHT;

        g.setColor(Color.WHITE);
        g.fillRect(SELL_ALL_BTN_X, btnY, SELL_ALL_BTN_WIDTH, SELL_ALL_BTN_HEIGHT);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("SELL COMMON", SELL_ALL_BTN_X + 5, btnY + 25);
    }

    public void setGameState(GameState gs) {
        this.gs = gs;
    }

}
