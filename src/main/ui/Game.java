package ui;

import model.Dealer;
import model.Session;
import model.User;
import model.History;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
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
            user = new User(jsonReader.read().getUserMoney());
        } catch (IOException e) {
            System.out.println("Error! Something is up!");
        }

        history = new History();

        startGame();
    }

    // EFFECTS: processes user inputs
    public void processInput() {
        input = new Scanner(System.in);
        String choice = input.next().toLowerCase();

        if (!runChoice(choice)) {
            isPlaying = false;
            System.out.println("Input not valid!");
        }
    }

    public boolean runChoice(String choice) {
        switch (choice) {
            case "p":
                isPlaying = true;
                startGame();
                break;
            case "c": isPlaying = true;
                break;
            case "b": System.out.println("Your balance is: $" + user.getMoney());
                break;
            case "l": loadSession();
                break;
            case "h": showHistory();
                break;
            case  "q": quitGame();
                break;
            case "x": isPlaying = false;
                break;
            default: return false;
        }
        return true;
    }

    public void openMenu() {
        System.out.println("Welcome to Jack n' Co!");
        System.out.println("\t(p) Play Game");
        System.out.println("\t(b) Check Balance");
        System.out.println("\t(l) Load Last Session History");
        System.out.println("\t(h) Check Session History");
        System.out.println("\t(q) Quit Game");
        processInput();
    }

    public void inGameMenu() {
        System.out.println("(c) Continue");
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
        System.out.println("Would you like to save session history? [y/n]");
        Scanner in = new Scanner(System.in);
        String input = in.next().toLowerCase();


        if (input.equals("y")) {
            saveSession();
        } else if (input.equals("n")) {
            //
        } else {
            System.out.println("Input not valid!");
            quitGame();
        }

//        try {
//            jsonWriter.write(user);
//            jsonWriter.close();
//        } catch (IOException e) {
//            System.out.println("Cannot read file!");
//        }

        System.out.println("Exiting game...");
        System.exit(0);
    }

    public void saveSession() {
        Session session = new Session(user.getMoney(), history.getHistory());
        try {
            jsonWriter.open();
            jsonWriter.write(session);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadSession() {
        System.out.println("Loading History...");
        try {
            history.addHistory(jsonReader.read().getHistories());
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
