package model;


// In a deck
// There is 2 suits
// 10 numbers, 1 jack, 1 queen, 1 king,
// I'll count ace as 1 for now!!!

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Decks {
    private Suits suits;
    private Ranks ranks;

    private static int NUMOFCARDS = 52;

    private List<Cards> deckOfCards;
    private Random random;

    // EFFECTS: construct a deck that consist of 52 cards, each with its unique ranks and suits
    public Decks() {
        deckOfCards = new ArrayList<Cards>();
        random = new Random();

        for (Suits suit : suits.values()) {
            for (Ranks rank : ranks.values()) {
                deckOfCards.add(new Cards(suit, rank));
            }
        }

    }

    // EFFECTS: return a deck of cards
    public List<Cards> getDeckOfCards() {
        return deckOfCards;
    }

    // EFFECTS: randomize the card
    public Cards getRandomCard() {
        int randomNumber = random.nextInt(NUMOFCARDS);
        return this.deckOfCards.get(randomNumber);
    }
}
