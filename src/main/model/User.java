package model;

import model.exceptions.NegativeMoneyException;
import model.exceptions.NoMoneyException;

import java.util.ArrayList;
import java.util.List;

public class User implements Player {
    private int money;
    private List<Cards> hand;

    // EFFECTS: constructs a user with a default $1000 money and empty hand
    public User(int money) {
        this.money = money;
        hand = new ArrayList<>();
    }

    // REQUIRES: amount <= money and amount > 0
    // MODIFIES: this
    // EFFECTS:
    public int takeMoney(int amount) throws NoMoneyException, NegativeMoneyException {
        if (amount > money) {
            throw new NoMoneyException();
        } else if (amount <= 0) {
            throw new NegativeMoneyException();
        }

        money -= amount;
        return amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds the given amount to the user's money
    public void giveMoney(int amount) {
        this.money += amount;
    }

    // EFFECTS: gets the user's money
    public int getMoney() {
        return this.money;
    }


    // REQUIRES: card != null
    // MODIFIES: this
    // EFFECTS: add the card to the user's hand
    @Override
    public void addHand(Cards card) {
        hand.add(card);
    }

    // EFFECTS: returns the user's hand
    @Override
    public List<Cards> getHand() {
        return this.hand;
    }

    // EFFECTS: return a formatted version of user's hand
    @Override
    public String getFormatHand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your card: ");
        for (Cards eachCard : getHand()) {
            sb.append(eachCard.getFormatCard() + " ");
        }

        return sb.toString();
    }

    // EFFECTS: returns the total value of the cards that is in the user's hand
    @Override
    public int getValueOfHand() {
        int total = 0;
        for (Cards card : hand) {
            total += card.getValue();
        }

        return total;
    }

    // MODIFIES: this
    // EFFECTS: completely reset the hand by making a new list
    @Override
    public void resetHand() {
        hand = new ArrayList<>();
    }
}
