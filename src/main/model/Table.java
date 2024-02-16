package model;

public class Table {
    private User user;
    private Dealer dealer;
    private Decks decks;

    // EFFECTS: constructs a table that can connect between the player and the dealer
    public Table(User user, Dealer dealer) {
        this.user = user;
        this.dealer = dealer;

        decks = new Decks();
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
        System.out.printf("Your card is %s[] and %n", dealerCard2.getValue());

        dealer.giveOptions();
    }

    // EFFECTS: return a random card number and type
    public Cards drawCard() {
        return decks.getRandomCard();
    }

    // EFFECTS: compare the value of the dealer's hand and the player's hand and determine the winner
    public void compareValue() {

    }
}
