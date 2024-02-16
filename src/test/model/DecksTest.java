package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.function.DoubleConsumer;

import static org.junit.jupiter.api.Assertions.*;

public class DecksTest {
    Decks decks;

    @BeforeEach
    public void runBefore() {
        decks = new Decks();
    }

    @Test
    public void testConstructor() {
        assertFalse(decks.getDeckOfCards().isEmpty());
        assertEquals(52, decks.getDeckOfCards().size());
    }

    @Test
    public void testGetRandomCard() {
        assertTrue(decks.getDeckOfCards().contains(decks.getRandomCard()));
        assertTrue(decks.getDeckOfCards().contains(decks.getRandomCard()));
        assertFalse(decks.getDeckOfCards().contains(null));
    }
}
