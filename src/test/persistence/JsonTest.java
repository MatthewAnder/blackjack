package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
        } catch (IOException e) {
            // goes through
        }
    }

    @Test
    public void testJsonEmptyHistory() {
        try {
            List<String> histories = new ArrayList<String>();
            Session session = new Session(1000, new History());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHistory.json");
            writer.open();
            writer.write(session);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHistory.json");
            session = reader.read();
            assertEquals(1000, session.getUserMoney());
            assertEquals(histories, session.getHistory());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testJsonWithHistory() {
        try {
            History history = new History();
            List<Cards> cards1 = new ArrayList<>();
            List<Cards> cards2 = new ArrayList<>();

            Cards card1 = new Cards(Suits.SPADE, Ranks.KING);
            Cards card2 = new Cards(Suits.HEART, Ranks.TEN);
            Cards card3 = new Cards(Suits.CLUB, Ranks.TWO);
            cards1.add(card1);
            cards1.add(card1);

            cards2.add(card2);
            cards2.add(card3);
            Game game = new Game(cards1, cards2, 10, true);

            history.putHistory(game);
            Session session = new Session(1000, history);
            JsonWriter writer = new JsonWriter("./data/testWriterWithHistory.json");
            writer.open();
            writer.write(session);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithHistory.json");
            session = reader.read();
            assertEquals(1000, session.getUserMoney());
//            assertEquals(game, session.getHistory());
        } catch (IOException e) {
            fail();
        }
    }
}
