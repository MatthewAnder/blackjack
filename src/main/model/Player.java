package model;

import java.util.LinkedList;

public class Player {
    private int money;
    private LinkedList<Cards> cards;
    private LinkedList<Chips> chips;

    public Player() {
        this.money = 0;
    }

    // EFFECTS: get the player's money
    public int getMoney() {
        return this.money;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: take the given amount of money from the player
    public void takeMoney(int amount) {
        this.money -= amount;
    }
}
