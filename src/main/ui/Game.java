package ui;

import model.Dealer;
import model.User;
import model.History;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    private static final String JSON_STORE = "./data/history.json";

    private boolean isPlaying;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private User user;
    private Dealer dealer;
    private Table table;
    private History history;

    // starts the game with opening the start menu
    public Game() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        try {
            user = new User(jsonReader.read().getMoney());
            history = new History();
        } catch (IOException e) {
            System.out.println("Error! Something is up!");
        }

        startGame();
    }

    // EFFECTS: processes user inputs
    public void processInput() {
        input = new Scanner(System.in);
        String choice = input.next().toLowerCase();

        switch (choice) {
            case "p":
                isPlaying = true;
                startGame();
                break;
            case "b":
                System.out.println("Your balance is: $" + user.getMoney());
                break;
            case "h":
                showHistory();
                break;
            case  "q":
                quitGame();
                break;
            case "x":
                isPlaying = false;
                break;
            default:
                System.out.println("Input is not valid!");
        }
    }

    public void openMenu() {
        System.out.println("Welcome to Jack n' Co!");
        System.out.println("(p) Play Game");
        System.out.println("(b) Check Balance");
        System.out.println("(h) Check Session History");
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
        while (!isPlaying) {
            openMenu();
        }

        while (isPlaying) {
            dealer = new Dealer();
            table = new Table(user, dealer, history);
            inGameMenu();
        }

        openMenu();
    }

    public void showHistory() {
        if (history.getHistory() != null) {
            System.out.println("History: \n");
            for (String h : history.getHistory()) {
                System.out.println(h);
            }
        } else if (history.getHistory() == null) {
            System.out.println("No History!");
        }
    }

    public void quitGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot read file!");
        }

        System.out.println("Exiting game...");
        System.exit(0);
    }
}
