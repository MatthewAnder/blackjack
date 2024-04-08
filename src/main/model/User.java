package model;

import model.exceptions.NegativeMoneyException;
import model.exceptions.NoMoneyException;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
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

        EventLog.getInstance().logEvent(new Event("$" + amount + " is taken from player's bank account."));
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
        EventLog.getInstance().logEvent(new Event(card.getRank() + " " + card.getSuits()
                + " is given to User"));
        hand.add(card);
    }

    // EFFECTS: returns the user's hand
    @Override
    public List<Cards> getHand() {
        return this.hand;
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
        EventLog.getInstance().logEvent(new Event("Resets User's hand"));
        hand = new ArrayList<>();
    }
}
