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


    //EFFECTS: give player an option to hit, stand, or double, then the dealer will play depending on the player's
    //         inputted choice
    public void giveOptions() {
        System.out.println("Do you want to hit, stand or double?");
        String choice =  scanner.next().toLowerCase();

        switch (choice) {
            case "stand":
                playStand();
                break;
            case "hit":
                System.out.println("HIT");
                break;
            case "double":
                System.out.println("DOUBLE");
                break;
            default:
                System.out.println("Input not valid");
        }
    }

    // EFFECTS: return a random card number and type
    public Cards drawCard() {
        return decks.getRandomCard();
    }

    // EFFECT: dealer will draw card if the value of the hand is still <= 15 and adds the value of the card
    //         the dealer's draw to his own hand.
    public void playStand() {
        while (getValueOfHand() <= 15) {
            Cards card = drawCard();
            System.out.println(getValueOfHand());
            addHand(card);
        }
        System.out.println(getValueOfHand());
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
