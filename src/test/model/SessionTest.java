package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    Session session;
    History history;
    Game game;

    @BeforeEach
    public void setUp() {
        history = new History();
        List<Cards> cards1 = new ArrayList<>();
        List<Cards> cards2 = new ArrayList<>();

        Cards card1 = new Cards(Suits.SPADE, Ranks.KING);
        Cards card2 = new Cards(Suits.HEART, Ranks.TEN);
        Cards card3 = new Cards(Suits.CLUB, Ranks.TWO);
        cards1.add(card1);
        cards1.add(card1);

        cards2.add(card2);
        cards2.add(card3);
        game = new Game(cards1, cards2, 10, true);

        history.putHistory(game);

        session = new Session(100, history);
    }

    @Test
    public void testGetMoney() {
        assertEquals(100, session.getUserMoney());
    }

    @Test
    public void testGetHistory() {
        assertEquals(game, session.getHistory().get(0));
    }

    @Test
    public void testJSONObject() {

    }

    @Test
    public void testJSONObjectArray() {
        JSONObject object = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Card1\nCard2\n+ $100\n");
        jsonArray.put("Card1\nCard2\n- $100\n");
    }
}
