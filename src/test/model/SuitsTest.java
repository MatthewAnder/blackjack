package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuitsTest {
    @Test
    public void testToString() {
        assertEquals("Spades", Suits.SPADE.toString());
        assertEquals("Clubs", Suits.CLUB.toString());
        assertEquals("Diamonds", Suits.DIAMOND.toString());
        assertEquals("Hearts", Suits.HEART.toString());
    }
}
