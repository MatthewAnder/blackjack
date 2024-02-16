package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardsTest {
    Cards cards;

    @BeforeEach
    public void runBefore() {
        cards = new Cards(Suits.SPADE, Ranks.KING);
    }

    @Test
    public void testGetValue() {
        assertEquals(10, cards.getValue());
    }

    @Test
    public void testGetSuits() {
        assertEquals("Spades", cards.getSuits());
    }

    @Test
    public  void testGetRank() {
        assertEquals("King", cards.getRank());
    }

    @Test
    public void testGetFormatCard() {
        assertEquals("King [SPADES]", cards.getFormatCard());
    }
}
