package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

public class Game implements Writable {
    List<Cards> userHands;
    List<Cards> dealerHands;
    int moneyOnTable;
    boolean status;

    // constructs a game with the user hand, dealer hand, the money that is bet on the table, and whether
    // the user won the game or not.
    public Game(List<Cards> userHands, List<Cards> dealerHands, int moneyOnTable, boolean status) {
        this.userHands = userHands;
        this.dealerHands = dealerHands;
        this.moneyOnTable = moneyOnTable;
        this.status = status;
    }

    // EFFECTS: return the user hand of this game
    public List<Cards> getUserHands() {
        return this.userHands;
    }

    // EFFECTS: return the dealer hand of this game
    public List<Cards> getDealerHands() {
        return this.dealerHands;
    }

    // EFFECTS: return the money that is bet on the table
    public int getMoneyOnTable() {
        return this.moneyOnTable;
    }

    // EFFECTS: return the outcome of the game as a win or not
    public boolean getStatusOfGame() {
        return this.status;
    }

    // EFFECTS: return the data as a JSON object, so it can be saved to a JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("win", status);
        json.put("money on table", moneyOnTable);
        json.put("user hand", userHandToJson());
        json.put("dealer hand", dealerHandToJson());

        return json;
    }

    // EFFECTS: return a JSON Array for the user hand
    private JSONArray userHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cards c : getUserHands()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: return a JSON Array for the dealer hand
    private JSONArray dealerHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cards c : getDealerHands()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
