package ui;

import model.Dealer;
import model.Player;

import java.util.Scanner;

public class Game {
    private boolean isPlaying;
    private Scanner input;

    private Player player;
    private Dealer dealer;

    // starts the game with opening the start menu
    public Game() {
        player = new Player();

        openMenu();
    }

    // EFFECTS: processes user inputs
    public void processInput() {
        input = new Scanner(System.in);
        String choice = input.next().toLowerCase();

        switch (choice) {
            case "p":
                startGame();
                break;
            case "b":
                System.out.println(player.getMoney());
                break;
            case  "q":
                System.out.println("Exiting game...");
                break;
            default:
                System.out.println("Input is not valid!");
                processInput();
                break;
        }
    }

    public void openMenu() {
        System.out.println("Welcome to Jack n' Co!");
        System.out.println("(p) Play Game");
        System.out.println("(b) Check Balance");
        System.out.println("(q) Quit Game");
        processInput();
    }

    // EFFECTS: starts the game
    public void startGame() {
        isPlaying = true;

        dealer = new Dealer();

//        while (isPlaying) {
//
//        }

        openMenu();
    }
}
