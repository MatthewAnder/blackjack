package model;

public enum Suits {
    SPADE("Spades"),
    CLUB("Clubs"),
    DIAMOND("Diamonds"),
    HEART("Hearts");

    private String name;

    Suits(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
