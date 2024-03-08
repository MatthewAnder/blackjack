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
    List<String> histories;
    int userMoney;

    // Usually constructs a session when user is quitting the game
    public Session(Integer userMoney, List<String> histories) {
        this.userMoney = userMoney;
        this.histories = histories;
    }

    // EFFECTS: returns the user money
    public int getUserMoney() {
        return userMoney;
    }

    // EFFECTS: returns the list of histories
    public List<String> getHistories() {
        return histories;
    }

    // EFFECTS: returns a JSON object from money and list of string so it can be written into a JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");

        json.put("time", dateFormat.format(new Date()));
        json.put("money", userMoney);
        json.put("histories", historiesToJson());
        return json;
    }

    // EFFECTS: returns histories in this history as a JSON array
    private JSONArray historiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String h : histories) {
            jsonArray.put(h);
        }

        return jsonArray;
    }
}