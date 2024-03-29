package persistence;

import jdk.jfr.Category;
import model.*;

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
        History history = new History();

        Session session = new Session(money, history);
        addGames(history, jsonObject);
        return session;
    }

    private void addGames(History history, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("histories");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(history, nextGame);
        }
    }

    private void addGame(History history, JSONObject jsonObject) {
        int moneyOnTable = jsonObject.getInt("money on table");
        Boolean status = jsonObject.getBoolean("win");
        List<Cards> userHand = new ArrayList<>();
        for (Object json : jsonObject.getJSONArray("user hand")) {
            userHand.add(getCard((JSONObject) json));
        }

        List<Cards> dealerHand = new ArrayList<>();
        for (Object json : jsonObject.getJSONArray("dealer hand")) {
            dealerHand.add(getCard((JSONObject) json));
        }

        history.putHistory(new Game(userHand, dealerHand, moneyOnTable, status));
    }

    private Cards getCard(JSONObject jsonObject) {
        return new Cards(getSuit(jsonObject), getRank(jsonObject));
    }

    private Suits getSuit(JSONObject jsonObject) {
        return Suits.valueOf(jsonObject.getString("suit"));
    }

    private Ranks getRank(JSONObject jsonObject) {
        return Ranks.valueOf(jsonObject.getString("rank"));
    }
}

