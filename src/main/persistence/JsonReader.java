package persistence;

import model.Session;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads history session and user's money from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads session from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Session read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSession(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses session from JSON object and returns it
    private Session parseSession(JSONObject jsonObject) {
        Integer money = jsonObject.getInt("money");
        List<String> histories = new ArrayList<String>();

        for (Object history : jsonObject.getJSONArray("histories")) {
            histories.add(history.toString());
        }

        Session session = new Session(money, histories);
        return session;
    }
}

