package ui;

import model.Cards;
import model.Dealer;
import model.Decks;
import model.User;
import model.exceptions.NegativeMoneyException;
import model.exceptions.NoMoneyException;

import java.util.InputMismatchException;
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

        try {
            placeBet();
        } catch (InputMismatchException e) {
            System.out.println("Continue and Try Again!");
        } catch (NoMoneyException e) {
            System.out.println("You do not have that money! Continue and Try Again.");
        } catch (NegativeMoneyException e) {
            System.out.println("Money is negative! Continue and Try Again");
        }
    }

    // REQUIRES: input is not a string
    // EFFECTS:
    public void placeBet() throws InputMismatchException, NoMoneyException, NegativeMoneyException {
        System.out.println("Your balance is : $" + user.getMoney());
        System.out.print("Place bet: $");
        int bet = scanner.nextInt();

        betOnTable = bet;

        user.takeMoney(bet);

        System.out.println("- $" + betOnTable + " from bank account");

        distributeCards();
    }

    // MODIFIES: this
    // EFFECTS: distribute cards to player and dealer
    public void distributeCards() {
        Cards userCard1 = decks.getRandomCard();
        Cards userCard2 = decks.getRandomCard();
        Cards dealerCard1 = decks.getRandomCard();
        Cards dealerCard2 = decks.getRandomCard();

        user.addHand(userCard1);
        user.addHand(userCard2);
        dealer.addHand(dealerCard1);
        dealer.addHand(dealerCard2);

        System.out.printf("Dealer card is %s and unknown %n", dealerCard1.getFormatCard());
        System.out.printf("Your card is %s and %s %n", userCard1.getFormatCard(), userCard2.getFormatCard());

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
            dealer.addHand(decks.getRandomCard());
        }
        System.out.println(dealer.getValueOfHand());
        checkHand();
    }

    public void playHit() {
        Cards card = decks.getRandomCard();
        user.addHand(card);

        System.out.println("You got " + card.getFormatCard());
        System.out.print("Cards in your hand is: ");
        for (Cards eachCard : user.getHand()) {
            System.out.printf(eachCard.getFormatCard() + " ");
        }
        System.out.printf("%n");

        if (user.getValueOfHand() >= 22) {
            System.out.println("Your total is " + user.getValueOfHand());
            System.out.println("You bust!");
            System.out.println("You lost $" + betOnTable);
        } else if (user.getValueOfHand() <= 21) {
            giveOptions();
        }
    }

    public void playDouble() {
        // TODO: THIS FEATURE IS VERY BUGGY
        betOnTable *= 2;
        user.addHand(decks.getRandomCard());
        playStand();
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
        } else if (user.getValueOfHand() <= dealer.getValueOfHand()) {
            System.out.println("You lose!");
            System.out.println("You lose $" + betOnTable);
        }
    }
}
