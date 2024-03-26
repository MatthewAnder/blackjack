package ui;

import model.Dealer;
import model.History;
import model.Session;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;

public class GameGUI extends JFrame {
    private static final String JSON_STORE = "./data/history.json";

    private boolean isPlaying;
    private Scanner input;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private User user;
    private Dealer dealer;
    private Table table;
    private History history;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static void main(String[] args) {
        new GameGUI();
    }

    public GameGUI() {
        super("Jack n' Co");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        try {
            user = new User(jsonReader.read().getUserMoney());
        } catch (IOException e) {
            System.out.println("Error! Something is up!");
        }

        history = new History();

        startGame();

        initializeGraphics();
        initializeHomeScreen();
    }

    public static void setUIFont(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setUIFont(new FontUIResource(new Font("Tahoma", Font.PLAIN, 13)));
    }

    private void  initializeHomeScreen() {
        JPanel panel = new JPanel();
        JLabel title = new JLabel();

        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.GREEN);
        GridBagConstraints constraints = new GridBagConstraints();

        title.setText("<html><h1>Welcome to Jack n' Co</h1></html>");

        panel.add(generateImage("./cards/king_of_clubs.png"));
        panel.add(generateImage("./cards/jack_of_clubs.png"));
        panel.add(generateImage("./cards/queen_of_clubs.png"));
        panel.add(generateImage("./cards/ace_of_clubs.png"));

        initializeConstraints(constraints);
        panel.add(title);

        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> startGame());
        JButton checkHistoryBtn = new JButton("Check History");
        checkHistoryBtn.addActionListener(e -> saveSession());
        JButton loadHistoryBtn = new JButton("Load History");
        loadHistoryBtn.addActionListener(e -> loadSession());
        JButton exitBtn = new JButton("Exit Game");
        exitBtn.addActionListener(e -> System.exit(0));

        addBtn(panel, constraints, startBtn, 1);
        addBtn(panel, constraints, loadHistoryBtn, 2);
        addBtn(panel, constraints, checkHistoryBtn, 3);
        addBtn(panel, constraints, exitBtn, 4);

        add(panel);
    }

    private void initializeConstraints(GridBagConstraints c) {
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(JPanel panel, GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridx = 0;
        constraints.gridy = padY;
        panel.add(btn, constraints);
    }

    // EFFECTS: generates a consistently formatted image icon for the playing cards
    private JLabel generateImage(String path) {
        ImageIcon i = new ImageIcon(ClassLoader.getSystemResource(path));
        Image image = i.getImage();
        Image newImg = image.getScaledInstance(90, 120, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(newImg));
        imgLabel.setBackground(Color.white);
        imgLabel.setOpaque(true);
        imgLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
        return imgLabel;
    }

    // EFFECTS: starts the game
    public void startGame() {

    }

    // EFFECTS:
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

    // EFFECTS:
    public void loadSession() {
        System.out.println("Loading History...");
        try {
            history.addHistory(jsonReader.read().getHistories());
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }
}
