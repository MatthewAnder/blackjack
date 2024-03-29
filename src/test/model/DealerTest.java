package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DealerTest {
    Dealer dealer;

    @BeforeEach
    public void runBefore() {
        dealer = new Dealer();
    }

    @Test
    public void testAddHand() {
        Cards card1 = new Cards(Suits.CLUB, Ranks.FOUR);
        Cards card2 = new Cards(Suits.SPADE, Ranks.KING);
        dealer.addHand(card1);
        dealer.addHand(card2);

        assertEquals(card1, dealer.getHand().get(0));
        assertEquals(card2, dealer.getHand().get(1));
    }

    @Test
    public void testGetHand() {
        Cards card1 = new Cards(Suits.CLUB, Ranks.FOUR);
        Cards card2 = new Cards(Suits.SPADE, Ranks.KING);
        dealer.addHand(card1);
        dealer.addHand(card2);

        assertEquals(card1, dealer.getHand().get(0));
        assertEquals(card2, dealer.getHand().get(1));
    }

    @Test
    public void testGetValueOfHand() {
        assertEquals(0, dealer.getValueOfHand());
        dealer.addHand(new Cards(Suits.SPADE, Ranks.FIVE));
        assertEquals(5, dealer.getValueOfHand());
    }

    @Test
    public void testResetHand() {
        Cards card1 = new Cards(Suits.CLUB, Ranks.FOUR);
        Cards card2 = new Cards(Suits.SPADE, Ranks.KING);
        dealer.addHand(card1);
        dealer.addHand(card2);

        assertEquals(card1, dealer.getHand().get(0));
        assertEquals(card2, dealer.getHand().get(1));
        assertEquals(2, dealer.getHand().size());
        dealer.resetHand();
        assertEquals(0, dealer.getHand().size());
    }
}
