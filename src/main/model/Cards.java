package model;

import org.json.JSONObject;
import persistence.Writable;

import java.security.PublicKey;

public class Cards implements Writable {
    private Suits suits;
    private Ranks ranks;

    public Cards(Suits suits, Ranks ranks) {
        this.suits = suits;
        this.ranks = ranks;
    }

    // EFFECTS: return the value of a card
    public int getValue() {
        return ranks.getValue();
    }

    // EFFECTS: get the suit of the card
    public String getSuits() {
        return suits.toString();
    }

    // EFFECTS: get the rank of the card
    public String getRank() {
        return ranks.toString();
    }

    // EFFECTS: return a formatted string for the card to maintain consistency when printing the card
    public String getFormatCard() {
        return this.ranks + " [" + this.suits.toString().toUpperCase() + "]";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("rank", ranks);
        json.put("suit", suits);

        return json;
    }
}


