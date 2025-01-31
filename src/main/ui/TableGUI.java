package ui;

import model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TableGUI extends JPanel {
    private JPanel pages;
    private CardLayout pagesLayout;
    private int moneyOnTable;
    private History history;
    private User user;
    private Dealer dealer;
    private Decks decks;

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    public TableGUI(JPanel pages, CardLayout pagesLayout, int moneyOnTable, History history, User user) {
        this.pages = pages;
        this.pagesLayout = pagesLayout;
        this.moneyOnTable = moneyOnTable;
        this.history = history;
        this.user = user;
        this.dealer = new Dealer();

        initializeFields();
        distributeCards();
        initializeGraphics();
    }

    // EFFECTS: initializing fields
    private void initializeFields() {
        decks = new Decks();
        user.resetHand();
        dealer.resetHand();
    }

    // EFFECTS: initializing the layouts
    private void initializeGraphics() {
        setVisible(true);
        setLayout(new BorderLayout());

        initializeTopPanel();
        initializeMiddlePanel();
        initializeBottomPanel();
    }

    // EFFECTS: a helper method to create a JPanel with text and margin
    private JPanel createPanelWithText(String text, int top, int bottom) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(top, 20, bottom, 20));
        return panel;
    }

    // EFFECTS: initializing the top panel
    private void initializeTopPanel() {
        topPanel = createPanelWithText("Dealer's Hand:", 100, 10);

        // Create a panel for the dealer's hand text
        JPanel dealerHandTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel dealerHandTextLabel = new JLabel("Dealer's Hand:");
        dealerHandTextPanel.add(dealerHandTextLabel);
        topPanel.add(dealerHandTextPanel, BorderLayout.NORTH);

        // Create a panel for the card images
        JPanel cardImagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel firstCardLabel = generateImage(getImagePath(dealer.getHand().get(0)));
        JLabel backCardLabel = generateImage("cards/back.png");
        cardImagesPanel.add(firstCardLabel);
        cardImagesPanel.add(backCardLabel);
        topPanel.add(cardImagesPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
    }

    // EFFECTS: reveals the dealer hand by recalling the top panel and showing all cards
    private void revealDealerHand() {
        topPanel.removeAll();
        topPanel = createPanelWithText("Dealer's Hand:", 100, 10);

        // Create a panel for the dealer's hand text
        JPanel dealerHandTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel dealerHandTextLabel = new JLabel("Dealer's Hand:");
        dealerHandTextPanel.add(dealerHandTextLabel);
        topPanel.add(dealerHandTextPanel, BorderLayout.NORTH);

        // Create a panel for the card images
        JPanel cardImagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (int i = 0; i < dealer.getHand().size(); i++) {
            cardImagesPanel.add(generateImage(getImagePath(dealer.getHand().get(i))));
        }

        revalidate();
        topPanel.add(cardImagesPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
    }

    // EFFECTS: initializing the middle panel
    private void initializeMiddlePanel() {
        middlePanel = createPanelWithText("Total Bet: $" + moneyOnTable, 20, 20);
        add(middlePanel, BorderLayout.CENTER);
    }

    // EFFECTS: initializing the bottom panel
    private void initializeBottomPanel() {
        bottomPanel = createPanelWithText("User's Hand:", 10, 100);

        // Create a panel for the dealer's hand text
        JPanel userHandTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel userHandTextLabel = new JLabel("User's Hand:");
        userHandTextPanel.add(userHandTextLabel);
        bottomPanel.add(userHandTextPanel, BorderLayout.NORTH);

        // Create a panel for the card images
        JPanel cardImagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (int i = 0; i < user.getHand().size(); i++) {
            cardImagesPanel.add(generateImage(getImagePath(user.getHand().get(i))));
        }
        bottomPanel.add(cardImagesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        initializeButtons(buttonPanel);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        revalidate();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initialize the play buttons
    private void initializeButtons(JPanel panel) {
        JButton hitBtn = new JButton("HIT");
        hitBtn.addActionListener(e -> playHit());
        JButton standBtn = new JButton("STAND");
        standBtn.addActionListener(e -> playStand());
        JButton doubleBtn = new JButton("DOUBLE");
        doubleBtn.addActionListener(e -> playDouble());
        panel.add(hitBtn);
        panel.add(standBtn);
        panel.add(doubleBtn);
    }

    // MODIFIES: this
    // EFFECTS: distribute cards to the player and the dealer
    public void distributeCards() {
        user.addHand(decks.getRandomCard());
        user.addHand(decks.getRandomCard());
        dealer.addHand(decks.getRandomCard());
        dealer.addHand(decks.getRandomCard());
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the user hand and frequently check whether player will bust or get a jackpot
    public void playHit() {
        user.addHand(decks.getRandomCard());
        initializeBottomPanel();

        if (user.getValueOfHand() > 21) {
            finishGame("user bust", false);
        } else if (user.getValueOfHand() == 21) {
            moneyOnTable *= 2;
            finishGame("jackpot", true);
        }
    }

    // MODIFIES: this
    // EFFECTS: dealer will draw a card until the dealer bust or get a jackpot
    public void playStand() {
        while (dealer.getValueOfHand() <= 15) {
            dealer.addHand(decks.getRandomCard());
        }
        revealDealerHand();
        checkHand();
    }

    // MODIFIES: this
    // EFFECTS: double the bet on the table and adds a card to the user hand, it will immediately check if the player
    //          wins or not
    public void playDouble() {
        moneyOnTable *= 2;
        try {
            user.takeMoney(moneyOnTable);
        } catch (Exception e) {
            System.out.println("Game is taking invalid number!");
        }

        user.addHand(decks.getRandomCard());
        initializeMiddlePanel();
        initializeBottomPanel();
        playStand();
    }

    // EFFECTS: return an image path to the given card
    public String getImagePath(Cards card) {
        return "cards/"
                + card.getRank().toLowerCase()
                + "_of_"
                + card.getSuits().toLowerCase()
                + ".png";
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

    // EFFECTS: compare the value of the dealer's hand and the user's hand and determine the winner
    public void checkHand() {
        if (dealer.getValueOfHand() >= 22) {
            System.out.println("Dealer busted!");
            finishGame("dealer bust", true);
        } else if (user.getValueOfHand() > dealer.getValueOfHand()) {
            System.out.println("You win!");
            finishGame("win", true);
        } else if (user.getValueOfHand() <= dealer.getValueOfHand()) {
            System.out.println("You lose!");
            finishGame("lose", false);
        }
    }

    // REQUIRES: win != ""
    // MODIFIES: this
    // EFFECTS: finish the game by giving or taking the user's money and putting it in the history
    public void finishGame(String win, boolean isWin) {
        if (isWin) {
            moneyOnTable *= 2;
            user.giveMoney(moneyOnTable * 2);
        }
        checkStatus(win.toLowerCase());
        history.putHistory(new Game(user.getHand(), dealer.getHand(), moneyOnTable, isWin));

        pagesLayout.show(pages, HomeGUI.HOME_PANEL);
        pages.remove(this);
    }

    // EFFECTS: check the status of the game and show a popup message box to alert the player
    public void checkStatus(String status) {
        switch (status) {
            case "jackpot":
                showMsgBox("JACKPOT!!!");
                break;
            case "dealer bust":
                showMsgBox("Dealer Busted!!! You get $" + moneyOnTable);
                break;
            case "user bust":
                showMsgBox("You Busted!!! You lose $" + moneyOnTable);
                break;
            case "win":
                showMsgBox("You win! + $" + moneyOnTable);
                break;
            case "lose":
                showMsgBox("You lose! - $" + moneyOnTable);
                break;
        }
    }

    // EFFECTS: a helper to easily show the message box
    public void showMsgBox(String content) {
        JOptionPane.showMessageDialog(null, content);
    }
}
