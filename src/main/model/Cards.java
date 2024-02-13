package model;

public class Cards {
    private Suits suits;
    private Ranks ranks;

    public Cards(Suits suits, Ranks ranks) {
        this.suits = suits;
        this.ranks = ranks;
    }

    public int getValue() {
        return ranks.getValue();
    }

    public String getCardType() {
        return suits.toString();
    }
}


