package persistence;

import model.Session;
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
            Session session = new Session(1000, histories);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHistory.json");
            writer.open();
            writer.write(session);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHistory.json");
            session = reader.read();
            assertEquals(1000, session.getUserMoney());
            assertEquals(histories, session.getHistories());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testJsonWithHistory() {
        try {
            List<String> histories = new ArrayList<String>();
            histories.add("hello");
            histories.add("Hello world!");
            Session session = new Session(1000, histories);
            JsonWriter writer = new JsonWriter("./data/testWriterWithHistory.json");
            writer.open();
            writer.write(session);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithHistory.json");
            session = reader.read();
            assertEquals(1000, session.getUserMoney());
            assertEquals(histories, session.getHistories());
        } catch (IOException e) {
            fail();
        }
    }
}
