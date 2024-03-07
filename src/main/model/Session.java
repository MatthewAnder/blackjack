package model;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Session implements Writable {
    List<String> histories;
    int userMoney;

    public Session(Integer userMoney, List<String> histories) {
        this.userMoney = userMoney;
        this.histories = histories;
    }

    public int getUserMoney() {
        return userMoney;
    }

    public List<String> getHistories() {
        return histories;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");

        json.put("time", dateFormat.format(new Date()));
        json.put("money", userMoney);
        json.put("histories", historiesToJson());
        return json;
    }

    // EFFECTS: returns things in this history as a JSON array
    private JSONArray historiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String h : histories) {
            jsonArray.put(h);
        }

        return jsonArray;
    }
}
