package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTest {

    History history;

    @BeforeEach
    public void setUp() {
        history = new History();
    }

    @Test
    public void testPutAndGetHistory() {
        assertNull(history.getHistory());
        history.putHistory("Card1", "Card2", 100, true);
        assertEquals("Card1\nCard2\n+ $100\n", history.getHistory().get(0));

        history.putHistory("Card1", "Card2", 100, false);
        assertEquals("Card1\nCard2\n- $100\n", history.getHistory().get(1));
    }


}
