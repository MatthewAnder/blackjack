package model;


public class Dealer {
    //EFFECTS: construct a dealer
    public Dealer() {
        giveCards();
        giveOptions();

        playOption(Options.STAND);
        playOption(Options.HIT);
        playOption(Options.DOUBLE);
    }

    //EFFECTS: distribute cards to player and dealer
    public void giveCards() {
        System.out.println("My card is 8 and 9, yours is 3 and 4");
    }

    //EFFECTS: give player an option to hit, stand, or double
    public void giveOptions() {
        System.out.println("Do you want to hit, stand or double?");
    }

    //REQUIRES: options != null
    //MODIFIES: this
    //EFFECTS:
    public void playOption(Options options) {
        switch (options) {
            case STAND:
                System.out.println("STAND");
                break;
            case HIT:
                System.out.println("HIT");
                break;
            case DOUBLE:
                System.out.println("DOUBLE");
                break;
        }

    }

//    public void giveMoney() {
//
//    }
//
//    public void takeMoney() {
//
//    }
}
