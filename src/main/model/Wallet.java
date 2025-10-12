package model;

public class Wallet extends Item {

    private int coins;

    public Wallet() {
        super("Wallet", 5);
        coins = 0;
    }

    public void add(int n) {
        coins += n;
    }

    public int getCoins() {
        return coins;
    }
}
