package model;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<String> history;

    public History() {
        this.history = new ArrayList<>();
    }

    // REQUIRES: moneyOnTable > 0
    // MODIFIES: this
    // EFFECTS: put a history of a game in a form of string and is also formatted properly for good visuals
    public void putHistory(String userHand, String enemyHand, int moneyOnTable, boolean isWin) {
        StringBuilder sb = new StringBuilder();
        sb.append(userHand + "\n");
        sb.append(enemyHand + "\n");

        if (isWin) {
            sb.append("+ $" + moneyOnTable);
        } else {
            sb.append("- $" + moneyOnTable);
        }

        sb.append("\n");
        history.add(sb.toString());
    }

    // MODIFIES: this
    // EFFECTS: appends the given list of string into this list of string
    public void addHistory(List<String> history) {
        this.history.addAll(history);
    }

    // EFFECTS: returns null if history is empty; else returns the list of string
    public List<String> getHistory() {
        if (history.isEmpty()) {
            return null;
        }

        return history;
    }
}
