package model;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<Game> history;

    public History() {
        this.history = new ArrayList<>();
    }

    // REQUIRES: moneyOnTable > 0
    // MODIFIES: this
    // EFFECTS: put a history of a game in a form of string and is also formatted properly for good visuals
    public void putHistory(Game game) {
        EventLog.getInstance().logEvent(new Event("Add a game to the history"));
        history.add(game);
    }

    // MODIFIES: this
    // EFFECTS: appends the given list of string into this list of string
    public void addHistory(List<Game> history) {
        this.history.addAll(history);
    }

    // EFFECTS: returns null if history is empty; else returns the list of string
    public List<Game> getHistory() {
        return history;
    }
}
