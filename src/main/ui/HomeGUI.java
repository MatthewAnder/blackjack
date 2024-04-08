package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;

public class HomeGUI extends JFrame {
    public static final String HOME_PANEL = "Card with Home";
    public static final String BET_PANEL = "Card with Bet";
    public static final String HISTORY_PANEL = "Card with History";
    public static final String TABLE_PANEL = "Card with Actual Game";

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/history.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel pages;
    private JPanel homePanel;
    private CardLayout pagesLayout;

    private User user;
    private History history;

    public HomeGUI() {
        super("Jack n' Co");

        homePanel = new JPanel();

        initializeJson();
        initializeGraphics();
        initializeHomeScreen();
        initializePages();
    }

    // EFFECTS: reads the user money from the JSON data file
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

    // MODIFIES: this
    // EFFECTS: initializes card layout for pages
    private void initializePages() {
        pagesLayout = new CardLayout();
        pages = new JPanel(pagesLayout);

        pages.add(homePanel, HOME_PANEL);
        pages.add(new BetGUI(pages, pagesLayout, user, history), BET_PANEL);
        pages.add(new HistoryGUI(pages, pagesLayout, history), HISTORY_PANEL);

        add(pages);
        revalidate();
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
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitGame();
            }
        });
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

    // EFFECT: setting up the layout for the home page using GridBagLayout
    private void  initializeHomeScreen() {
        homePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        initializeText(constraints);
        initializeButtons(constraints);

        add(homePanel);
    }

    // EFFECTS: initializes the title and the money text
    private void initializeText(GridBagConstraints constraints) {
        constraints.insets = new Insets(20, 0, 0, 0);
        constraints.gridx = 1000;
        constraints.gridy = 0;

        constraints.anchor = GridBagConstraints.CENTER;
        JLabel title = new JLabel();
        title.setFont(new Font("MV Boli", Font.BOLD, 50));
        title.setText("Welcome to Jack n' Co");
        homePanel.add(title, constraints);

        constraints.anchor = GridBagConstraints.LAST_LINE_START;
        JLabel moneyText = new JLabel();
        moneyText.setFont(new Font("MV Boli", Font.PLAIN, 15));
        moneyText.setText("Money = " + user.getMoney());
        homePanel.add(moneyText, constraints);
    }

    // MODIFIES: this
    // EFFECTS: draw the button for the home page
    private void initializeButtons(GridBagConstraints constraints) {
        constraints.anchor = GridBagConstraints.CENTER;

        JButton startBtn = new JButton("Start Game");
        startBtn.addActionListener(e -> pagesLayout.show(pages, BET_PANEL));
        JButton checkHistoryBtn = new JButton("Check History");
        checkHistoryBtn.addActionListener(e -> goToHistory());
        JButton saveHistoryBtn = new JButton("Save History");
        saveHistoryBtn.addActionListener(e -> saveSession());
        JButton loadHistoryBtn = new JButton("Load History");
        loadHistoryBtn.addActionListener(e -> loadSession());
        JButton exitBtn = new JButton("Exit Game");
        exitBtn.addActionListener(e -> quitGame());

        addBtn(constraints, startBtn, 2);
        addBtn(constraints, checkHistoryBtn, 3);
        addBtn(constraints, saveHistoryBtn, 4);
        addBtn(constraints, loadHistoryBtn, 5);
        addBtn(constraints, exitBtn, 6);
    }

    // EFFECTS: changes the card layout to the history panel
    public void goToHistory() {
        pagesLayout.show(pages, HISTORY_PANEL);
    }

    // EFFECTS: quits the game and prints all of the events in the event log
    public void quitGame() {
        for (model.Event event : EventLog.getInstance()) {
            System.out.println(event.getDescription());
        }
        EventLog.getInstance().clear();
        dispose();
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: a helper to add a button with y padding and its constraints
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridy = padY;
        homePanel.add(btn, constraints);
    }

    // EFFECTS: saves the data into a JSON file
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

    // EFFECTS: load session history and showing a popup message that indicates the process
    public void loadSession() {
        System.out.println("Loading History...");
        try {
            history.addHistory(jsonReader.read().getHistory());
            JOptionPane.showMessageDialog(null, "History is loaded!");
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }
}
