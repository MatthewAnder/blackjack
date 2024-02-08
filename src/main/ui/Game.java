package ui;

import model.Dealer;

import java.util.ArrayList;

public class Game {

    public Game() {
        welcomePlayer();
        new Dealer();
    }

    public void welcomePlayer() {
        System.out.println("Hello! Welcome to Jack n' Co!");
    }
}
