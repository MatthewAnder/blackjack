package ui;

import model.Dealer;
import model.User;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Scanner;

public class Game {
    private boolean isPlaying;
    private Scanner input;

    private User user;
    private Dealer dealer;
    private Table table;

    // starts the game with opening the start menu
    public Game() {
        user = new User();

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
                System.out.println("Your balance is: $" + user.getMoney());
                openMenu();
                break;
            case  "q":
                System.out.println("Exiting game...");
                System.exit(0);
                break;
            case "x":
                isPlaying = false;
                openMenu();
                break;
            default:
                System.out.println("Input is not valid!");
        }
    }

    public void openMenu() {
        System.out.println("Welcome to Jack n' Co!");
        System.out.println("(p) Play Game");
        System.out.println("(b) Check Balance");
        System.out.println("(q) Quit Game");
        processInput();
    }

    public void inGameMenu() {
        System.out.println("(p) Continue");
        System.out.println("(x) Back to Start Menu");
        processInput();
    }

    // EFFECTS: starts the game
    public void startGame() {
        isPlaying = true;

        while (isPlaying) {
            dealer = new Dealer();
            table = new Table(user, dealer);
            inGameMenu();
        }

        openMenu();
    }
}
