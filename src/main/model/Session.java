package model;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Session implements Writable {
    List<History> histories;

    public Session() {
        histories = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd");

        json.put("time", dateFormat.format(new Date()));
        json.put("histories", historiesToJson());
        return json;
    }

    // EFFECTS: returns things in this history as a JSON array
    private JSONArray historiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (History h : histories) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }
}
