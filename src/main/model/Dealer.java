package model;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Player {
    private List<Cards> hand;

    //EFFECTS: construct a dealer
    public Dealer() {
        hand = new ArrayList<>();
    }

    // REQUIRES: card is not null
    // MODIFIES: this
    // EFFECTS: add the card to the dealer's hand
    @Override
    public void addHand(Cards card) {
        hand.add(card);
    }

    // EFFECTS: returns the dealer's hand
    @Override
    public List<Cards> getHand() {
        return hand;
    }

    public String getFormatHand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dealer's card: ");
        for (Cards eachCard : getHand()) {
            sb.append(eachCard.getFormatCard() + " ");
        }

        return sb.toString();
    }

    // EFFECTS: returns the total value of the cards that are in the dealer's hand
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
