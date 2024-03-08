package model;

import java.util.ArrayList;
import java.util.List;

public class History {
    List<String> history;

    public History() {
        this.history = new ArrayList<>();
    }

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

    public void addHistory(List<String> history) {
        this.history.addAll(history);
    }

    public List<String> getHistory() {
        if (history.isEmpty()) {
            return null;
        }

        return history;
    }
}
