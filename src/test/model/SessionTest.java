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

    @BeforeEach
    public void setUp() {
        history = new History();
        history.putHistory("Card1", "Card2", 100, true);
        history.putHistory("Card1", "Card2", 100, false);

        session = new Session(100, history.getHistory());
    }

    @Test
    public void testGetMoney() {
        assertEquals(100, session.getUserMoney());
    }

    @Test
    public void testGetHistory() {
        List<String> histories = new ArrayList<>();
        histories.add("Card1\nCard2\n+ $100\n");
        histories.add("Card1\nCard2\n- $100\n");

        assertEquals(histories, session.getHistory());
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
