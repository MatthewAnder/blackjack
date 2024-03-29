package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTest {

    History history;
    List<Cards> cards1;
    List<Cards> cards2;
    Cards card1;
    Cards card2;
    Cards card3;
    Cards card4;

    @BeforeEach
    public void setUp() {
        history = new History();

        cards1 = new ArrayList<>();
        cards2 = new ArrayList<>();

        cards1.add(card1);
        cards1.add(card2);
        cards2.add(card3);
        cards2.add(card4);
    }

    @Test
    public void testPutAndGetHistory() {
        assertEquals(new ArrayList<Game>(), history.getHistory());

        history.putHistory(new Game(cards1, cards2, 10, true));
        assertEquals(cards1, history.getHistory().get(0).getUserHands());

        history.putHistory(new Game(cards2, cards1, 100, false));
        assertEquals(cards2, history.getHistory().get(0).getUserHands());
    }

    @Test
    public void testAddHistory() {
        assertTrue(history.getHistory().isEmpty());
        List<Game> histories = new ArrayList<>();
        Game game1 = new Game(cards1, cards2, 10, true);
        Game game2 = new Game(cards2, cards1, 100, false);
        histories.add(game1);
        histories.add(game2);

        history.addHistory(histories);
        assertEquals(game1, history.getHistory().get(0));
        assertEquals(game2, history.getHistory().get(1));
    }
}
