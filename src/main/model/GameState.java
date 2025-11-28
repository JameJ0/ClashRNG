package model;

import org.json.*;

import persistence.Writable;
import ui.Assets;

import java.awt.event.KeyEvent;

import java.util.*;

// represents the state of the game as a list of enemies and the player. 
// Also handles key presses and all the updates
public class GameState implements Writable {
    private Player player;
    private List<Enemy> enemies;
    private List<Item> droppedItems;

    public static final int WIDTH = 1600;
    public static final int HEIGHT = 900;

    private Random rand;

    private Status gameStatus;

    // EFFECTS: creates a new game and initialize entites
    public GameState() {
        this.gameStatus = Status.MENU;
        rand = new Random();
        enemies = new ArrayList<>();
        droppedItems = new ArrayList<>();

        player = new Knight("Name", Assets.getInstance().getKnightSprite());
    }

    // MODIFIES: this
    // changes the positions of the entites in the game
    public void update() {
        checkItemCollection();
        player.update();
        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);
            enemy.update();
            if (player.colliding(enemy)) {
                enemy.takeTrueDamage(player);
                player.takeTrueDamage(enemy);
            }
            if (enemy.getHealth() <= 0) {
                generateDrop(enemy);
                player.addLoot(enemy.getLoot());
                enemies.remove(i);
            }
        }
        if (player.getHealth() <= 0) {
            gameStatus = Status.GAME_OVER;
        }
        respawnEnemies();
    }

    // MODIFIES: this
    // EFFECTS: sell all the common items in inventory
    public void discardCommonItems() {
        List<Item> noCommon = new ArrayList<>();
        int size = player.getInventory().size();
        for (Item i : player.getInventory()) {
            if (i.getRarity() != Rarity.COMMON) {
                noCommon.add(i);
            }
        }
        player.setInventory(noCommon);
        player.addLoot(size - noCommon.size());
    }

    // MODIFIES: this
    // EFFECTS: checks if the player is touching any dropped item; if so, adds item
    // to inventory and safely removes it from the droppedItems list.
    private void checkItemCollection() {
        for (int i = droppedItems.size() - 1; i >= 0; i--) {
            Item item = droppedItems.get(i);

            int itemW = item.getImage().getWidth(null);
            int itemH = item.getImage().getHeight(null);

            if (player.colliding(item.getX(), item.getY(), itemW, itemH)) {
                player.addToInventory(item);
                droppedItems.remove(i);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: generate a drop based on rng, and add it to the ground (dropped
    // Items)
    public void generateDrop(Enemy e) {
        double rng = rand.nextDouble() * 100;
        if (rng < 50) {
            Item i = e.generateDrop(rng);
            i.setX(e.getX());
            i.setY(e.getY());
            droppedItems.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: when there are less than 5 enemies on screen, regenerate more
    // enemies.
    public void respawnEnemies() {
        if (enemies.size() < 5) {
            for (int i = 0; i < 5 - enemies.size(); i++) {
                Goblin g = new Goblin(Assets.getInstance().getGoblinSprite());
                g.setX(rand.nextInt(GameState.WIDTH));
                g.setY(rand.nextInt(GameState.HEIGHT));
                while (player.colliding(g)) {
                    g.setX(rand.nextInt(GameState.WIDTH));
                    g.setY(rand.nextInt(GameState.HEIGHT));
                }
                enemies.add(g);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: clears all the enemies on field
    public void clearAllEnemies() {
        enemies.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public Status getStatus() {
        return gameStatus;
    }

    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    public List<Item> getDroppedItems() {
        return droppedItems;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setStatus(Status status) {
        gameStatus = status;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    // MODIFIES: this
    // EFFECTS: set the velocity of the player based in the key input
    public void keyPressed(int keyCode) {
        int speed = player.getSpeed();
        if (keyCode == KeyEvent.VK_W) {
            player.setDY(-speed);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDY(speed);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setDX(-speed);
        }
        if (keyCode == KeyEvent.VK_D) {
            player.setDX(speed);
        }
    }

    // MODIFIES: this
    // EFFECTS: stops the player when the key is being released
    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_S) {
            player.setDY(0);
        }
        if (keyCode == KeyEvent.VK_A) {
            player.setDX(0);
        }
        if (keyCode == KeyEvent.VK_D) {
            player.setDX(0);
        }
    }

    // EFFECTS: returns the object as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", player.toJson());
        json.put("enemies", enemiesToJson());
        return json;
    }

    // EFFECTS: returns the enemies as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Enemy enemy : enemies) {
            jsonArray.put(enemy.toJson());
        }
        return jsonArray;
    }

    // an enum class to show the state of the game, in order to tell GamePanel
    // what to draw in case of each state.
    public enum Status {
        MENU, PLAYING, GAME_OVER
    }

}
