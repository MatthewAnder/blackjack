package model;

import java.util.LinkedList;
import java.util.List;

public class User implements Player {
    private int money;
    private LinkedList<Cards> cards;
    private LinkedList<Chips> chips;

    public User() {
        this.money = 0;
    }

    public int getMoney() {
        return this.money;
    }

    // MODIFIES: this
    // EFFECTS: add the card to the dealer's hand
    @Override
    public void addHand(Cards card) {

    }

    @Override
    public List<Cards> getHand() {
        return null;
    }

    @Override
    public int getValueOfHand() {
        return 0;
    }
}
