package model;

import model.exceptions.NoMoneyException;

import java.util.Scanner;

public class Table {
    private User user;
    private Dealer dealer;
    private Decks decks;

    private int betOnTable;

    private Scanner scanner;

    // EFFECTS: constructs a table that can connect between the player and the dealer
    public Table(User user, Dealer dealer) {
        this.user = user;
        this.dealer = dealer;
        user.resetHand();
        dealer.resetHand();

        scanner = new Scanner(System.in);

        decks = new Decks();
        placeBet();
    }

    public void placeBet() {
        System.out.print("Place bet: $");
        int bet = scanner.nextInt();

        betOnTable = bet;

        try {
            user.takeMoney(bet);
        } catch (NoMoneyException e) {
            System.out.println("Balance not valid! Bet again.");
            placeBet();
        }

        System.out.println("- $" + betOnTable + " from bank account");

        distributeCards();
    }

    // MODIFIES: this
    // EFFECTS: distribute cards to player and dealer
    public void distributeCards() {
        Cards userCard1 = drawCard();
        Cards userCard2 = drawCard();
        Cards dealerCard1 = drawCard();
        Cards dealerCard2 = drawCard();

        user.addHand(userCard1);
        user.addHand(userCard2);
        dealer.addHand(dealerCard1);
        dealer.addHand(dealerCard2);

        System.out.printf("Dealer card is %s[%S] and unknown %n", dealerCard1.getValue(), dealerCard1.getSuits());
        System.out.printf("Your card is %s[] and %s %n", userCard1.getValue(), userCard2.getValue());

        giveOptions();
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
                playHit();
                break;
            case "double":
                playDouble();
                break;
            default:
                System.out.println("Input not valid");
        }
    }

    // EFFECT: dealer will draw card if the value of the hand is still <= 15 and adds the value of the card
    //         the dealer's draw to his own hand.
    public void playStand() {
        while (dealer.getValueOfHand() <= 15) {
            dealer.addHand(drawCard());
        }
        System.out.println(dealer.getValueOfHand());
        checkHand();
    }

    public void playHit() {
        Cards card = drawCard();
        user.addHand(card);
        System.out.printf("You draw %s [%S] %n", card.getRank(), card.getSuits());

        if (user.getValueOfHand() >= 22) {
            System.out.println("Your total is " + user.getValueOfHand());
            System.out.println("You bust!");
            System.out.println("You lost $" + betOnTable);
        } else if (user.getValueOfHand() <= 21) {
            System.out.println(user.getValueOfHand());
            giveOptions();
        }
    }

    public void playDouble() {
        betOnTable *= 2;
        user.addHand(drawCard());
        playStand();
    }

    // EFFECTS: return a random card number and type
    public Cards drawCard() {
        return decks.getRandomCard();
    }

    // EFFECTS: compare the value of the dealer's hand and the user's hand and determine the winner
    public void checkHand() {
        if (user.getValueOfHand() >= 22) {
            System.out.println("You busted!");
        } else if (dealer.getValueOfHand() >= 22) {
            System.out.println("Dealer busted!");
            System.out.println("You get $" + betOnTable * 2);
            user.giveMoney(betOnTable * 2);
        } else if (user.getValueOfHand() > dealer.getValueOfHand()) {
            System.out.println("You win!");
            System.out.println("You get $" + betOnTable * 2);
            user.giveMoney(betOnTable * 2);
        } else if (user.getValueOfHand() < dealer.getValueOfHand()) {
            System.out.println("You lose!");
            System.out.println("You lose $" + betOnTable);
        }
    }
}
