package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dealer implements Player {
    private List<Cards> hand;

    private Random random;
    private Scanner scanner;

    private Decks decks;


    //EFFECTS: construct a dealer
    public Dealer() {
        random = new Random();
        scanner = new Scanner(System.in);
        decks = new Decks();

        hand = new ArrayList<>();
    }

    // EFFECTS: return a random card number and type
    public Cards drawCard() {
        return decks.getRandomCard();
    }



    // REQUIRES: card is not null
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
