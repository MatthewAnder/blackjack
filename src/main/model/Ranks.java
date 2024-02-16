package model;

public enum Ranks {
    ACE("Ace", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10);

    private String name;
    private int value;

    Ranks(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
