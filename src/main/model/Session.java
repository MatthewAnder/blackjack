package model;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// Session object for combining all the data into one object for ease of use
public class Session implements Writable {
    History history;
    int userMoney;

    // Usually constructs a session when user is quitting the game
    public Session(Integer userMoney, History history) {
        this.userMoney = userMoney;
        this.history = history;
    }

    // EFFECTS: returns the user money
    public int getUserMoney() {
        return userMoney;
    }

    // EFFECTS: returns the list of histories
    public List<Game> getHistory() {
        return history.getHistory();
    }

    // EFFECTS: returns a JSON object from money and list of string so it can be written into a JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("money", userMoney);
        json.put("histories", historyToJson());
        return json;
    }


    private JSONArray historyToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : history.getHistory()) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}