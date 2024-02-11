package ui;

import model.Dealer;
import model.Player;

import java.util.Scanner;

public class Game {
    private boolean isPlaying;
    private Scanner input;

    // starts the game with opening the start menu
    public Game() {
        startMenu();
    }

    // EFFECTS: giving user choice of playing or quitting the game
    public void startMenu() {
        input = new Scanner(System.in);
        System.out.println("Welcome to Jack n' Co!");
        System.out.println("Press 1 to play. Press 2 to quit.");
        processInput();
    }

    // EFFECTS: processes user inputs
    public void processInput() {
        String choice = input.next();

        if (choice.equals("1")) {
            startGame();
        } else if (choice.equals("2")) {
            System.out.println("Exiting game...");
            System.exit(0);
        } else {
            System.out.println("Input is not valid!");
            processInput();
        }
    }

    // EFFECTS: starts the game
    public void startGame() {
        isPlaying = true;

        Dealer dealer = new Dealer();
        Player player = new Player();

        while (isPlaying) {
            System.out.println("A");

        }

        startMenu();
    }
}
