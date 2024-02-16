package model;

import model.exceptions.NoMoneyException;

import java.util.ArrayList;
import java.util.List;

public class User implements Player {
    private int money;
    private List<Cards> hand;
    private List<Cards> cards;
    private List<Chips> chips;

    public User() {
        this.money = 1000;
        hand = new ArrayList<>();
    }

    // REQUIRES: amount <= money
    // MODIFIES: this
    // EFFECTS:
    public int takeMoney(int amount) throws NoMoneyException {
        if (amount <= money && amount > 0) {
            money -= amount;
            return amount;
        }

        throw new NoMoneyException();
    }

    public void giveMoney(int amount) {
        money += amount;
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

    @Override
    public void resetHand() {
        hand = new ArrayList<>();
    }
}
