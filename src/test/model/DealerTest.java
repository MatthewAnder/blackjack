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
    public void testGetValueOfHand() {
        assertEquals(0, dealer.getValueOfHand());
        dealer.addHand(new Cards(Suits.SPADE, Ranks.FIVE));
        assertEquals(5, dealer.getValueOfHand());
    }
}
