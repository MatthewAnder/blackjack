package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User implements Player {
    private int money;
    private List<Cards> hand;
    private List<Cards> cards;
    private List<Chips> chips;

    public User() {
        this.money = 0;
        hand = new ArrayList<>();
    }

    public int getMoney() {
        return this.money;
    }

    // MODIFIES: this
    // EFFECTS: add the card to the dealer's hand
    @Override
    public void addHand(Cards card) {
        hand.add(card);
    }

    @Override
    public List<Cards> getHand() {
        return hand;
    }

    @Override
    public int getValueOfHand() {
        int total = 0;
        for (Cards card : hand) {
            total += card.getValue();
        }

        return total;
    }
}
