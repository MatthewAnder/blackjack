package model;

import java.util.List;

public interface Player {
    public void addHand(Cards card);

    public List<Cards> getHand();

    public int getValueOfHand();

    public void resetHand();
}
