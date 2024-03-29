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

    public Game(List<Cards> userHands, List<Cards> dealerHands, int moneyOnTable, boolean status) {
        this.userHands = userHands;
        this.dealerHands = dealerHands;
        this.moneyOnTable = moneyOnTable;
        this.status = status;
    }

    public List<Cards> getUserHands() {
        return this.userHands;
    }

    public List<Cards> getDealerHands() {
        return this.dealerHands;
    }

    public int getMoneyOnTable() {
        return this.moneyOnTable;
    }

    public boolean getStatusOfGame() {
        return this.status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("win", status);
        json.put("money on table", moneyOnTable);
        json.put("user hand", userHandToJson());
        json.put("dealer hand", dealerHandToJson());

        return json;
    }

    private JSONArray userHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cards c : getUserHands()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    private JSONArray dealerHandToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Cards c : getDealerHands()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
