package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testAddHistory() {
        List<String> histories = new ArrayList<>();
        histories.add("Card1\nCard2\n- $100\n");
        histories.add("Card1\nCard2\n+ $100\n");

        history.addHistory(histories);
        assertEquals("Card1\nCard2\n- $100\n", history.getHistory().get(0));
        assertEquals("Card1\nCard2\n+ $100\n", history.getHistory().get(1));
    }
}
