package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

public class History implements Writable {
    List<String> history;

    public History() {
        history = new ArrayList<>();
    }

    public void putHistory(String userHand, String enemyHand, int moneyOnTable, boolean isWin) {
        StringBuilder sb = new StringBuilder();
        sb.append(userHand + "\n");
        sb.append(enemyHand + "\n");

        if (isWin) {
            sb.append("$" + moneyOnTable);
        } else {
            sb.append("- $" + moneyOnTable);
        }

        sb.append("\n");
        history.add(sb.toString());
    }

    public List<String> getHistory() {
        if (history.isEmpty()) {
            return null;
        }

        return history;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("history", stringToJson());
        return json;
    }

    // EFFECTS: returns things in this history as a JSON array
    private JSONArray stringToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String h : history) {
            jsonArray.put(h);
        }

        return jsonArray;
    }
}
