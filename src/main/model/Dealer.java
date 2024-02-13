package model;


import java.util.Random;
import java.util.Scanner;

public class Dealer {
    private int totalValueOfHand;
    private Random random;
    private Scanner scanner;


    //EFFECTS: construct a dealer
    public Dealer() {
        random = new Random();
        scanner = new Scanner(System.in);

        totalValueOfHand = 0;
        giveCards();
        giveOptions();
    }

    //EFFECTS: distribute cards to player and dealer
    public void giveCards() {
        Integer cardValueCovered = drawCard().getValue();
        Integer cardValue = drawCard().getValue();

        System.out.println("My card is " + cardValue + " and the other is covered.");
        totalValueOfHand = cardValueCovered + cardValue;

        System.out.println("Your card is " + drawCard().getValue() + " and " + drawCard().getValue());
    }

    //EFFECTS: give player an option to hit, stand, or double
    public void giveOptions() {
        System.out.println("Do you want to hit, stand or double?");
        String choice =  scanner.next().toLowerCase();

        if (choice.equals("stand")) {
            playOption(choice);
        } else if (choice.equals("hit")) {
            playOption(choice);
        } else if (choice.equals("double")) {
            playOption(choice);
        } else {
            System.out.println("Input not valid!!!");
            giveOptions();
        }
    }

    //REQUIRES: options != null
    //MODIFIES: this
    //EFFECTS: dealer either give player more card, double player's money, or play for himself
    public void playOption(String options) {
        switch (options) {
            case "stand":
                playStand();
                break;
            case "hit":
                System.out.println("HIT");
                break;
            case "double":
                System.out.println("DOUBLE");
                break;
        }
    }

    // EFFECTS: return a random card number and type
    public Cards drawCard() {
        Suits randomSuit = Suits.values()[random.nextInt(Suits.values().length)];
        Ranks randomRank = Ranks.values()[random.nextInt(Ranks.values().length)];
        return new Cards(randomSuit, randomRank);
    }

    // EFFECT: dealer will draw card if the value of the hand is still <= 15 and adds the value of the card
    //         the dealer's draw to his own hand.
    public void playStand() {
        while (totalValueOfHand <= 15) {
            Integer cardValue = drawCard().getValue();
            System.out.println(cardValue);
            addHand(cardValue);
        }
        System.out.println(totalValueOfHand);
    }

    // MODIFIES: this
    // EFFECTS: add the amount given to the total value of the dealer's hand
    public void addHand(int amount) {
        totalValueOfHand += amount;
    }
}
