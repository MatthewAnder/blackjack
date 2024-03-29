package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;

public class HomeGUI extends JFrame {
    public static final String HOME_PANEL = "Card with Home";
    public static final String TABLE_PANEL = "Card with Table";
    public static final String HISTORY_PANEL = "Card with History";

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/history.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel pages;
    private CardLayout pagesLayout;

    private User user;
    private Dealer dealer;
    private History history;

    JPanel homePanel;

    public HomeGUI() {
        super("Jack n' Co");

        homePanel = new JPanel();

        initializeJson();
        initializeGraphics();
        initializeHomeScreen();
        initializePages();
    }

    private void initializeJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        try {
            user = new User(jsonReader.read().getUserMoney());
        } catch (IOException e) {
            System.out.println("Error! Something is up!");
        }

        history = new History();
    }

    private void initializePages() {
        pagesLayout = new CardLayout();
        pages = new JPanel(pagesLayout);
        pages.add(homePanel, HOME_PANEL);
        pages.add(new TableGUI(pages, pagesLayout), TABLE_PANEL);
        pages.add(new HistoryGUI(pages, pagesLayout, history), HISTORY_PANEL);

        add(pages);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
        setUIFont(new FontUIResource(new Font("MV Boli", Font.PLAIN, 13)));
    }

    // EFFECTS: setting the UI Font globally
    // Learned code from stack overflow
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

    private void  initializeHomeScreen() {
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        initializeText(constraints);
        initializeButtons(constraints);

        add(homePanel);
    }

    private void initializeText(GridBagConstraints constraints) {
        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridx = 1000;
        constraints.gridy = 0;

        constraints.anchor = constraints.CENTER;
        JLabel title = new JLabel();
        title.setFont(new Font("MV Boli", Font.BOLD, 50));
        title.setText("Welcome to Jack n' Co");
        homePanel.add(title, constraints);

        constraints.anchor = constraints.LAST_LINE_START;
        JLabel moneyText = new JLabel();
        moneyText.setFont(new Font("MV Boli", Font.PLAIN, 15));
        moneyText.setText("Money = " + user.getMoney());
        homePanel.add(moneyText, constraints);
    }

    private void initializeButtons(GridBagConstraints constraints) {
        constraints.anchor = constraints.CENTER;

        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> pagesLayout.show(pages, TABLE_PANEL));
        JButton checkHistoryBtn = new JButton("Check History");
        checkHistoryBtn.addActionListener(e -> goToHistory());
        JButton saveHistoryBtn = new JButton("Save History");
        saveHistoryBtn.addActionListener(e -> saveSession());
        JButton loadHistoryBtn = new JButton("Load History");
        loadHistoryBtn.addActionListener(e -> loadSession());
        JButton exitBtn = new JButton("Exit Game");
        exitBtn.addActionListener(e -> System.exit(0));

        addBtn(constraints, startBtn, 2);
        addBtn(constraints, checkHistoryBtn, 3);
        addBtn(constraints, saveHistoryBtn, 4);
        addBtn(constraints, loadHistoryBtn, 5);
        addBtn(constraints, exitBtn, 6);
    }

    public void goToHistory() {
        pagesLayout.show(pages, HISTORY_PANEL);
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridy = padY;
        homePanel.add(btn, constraints);
    }

    // EFFECTS:
    public void saveSession() {
        Session session = new Session(user.getMoney(), history);
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
            history.addHistory(jsonReader.read().getHistory());
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }
}
