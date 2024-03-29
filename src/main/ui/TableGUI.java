package ui;

import model.Cards;
import model.Decks;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableGUI extends JPanel {

    private int moneyOnTable;
    private Decks decks;
    private List<Cards> userHand;
    private List<Cards> dealerHand;

    private JPanel topPanel;
    private JPanel middlePanel;
    private JPanel bottomPanel;

    public TableGUI() {
        moneyOnTable = 0;
        decks = new Decks();
        userHand = new ArrayList<>();
        dealerHand = new ArrayList<>();

        distributeCards();
        initializeGraphics();
    }

    private void initializeGraphics() {
        setVisible(true);
        setLayout(new BorderLayout());

        initializeTopPanel();
        initializeMiddlePanel();
        initializeBottomPanel();
    }

    // Utility method to create a JPanel with text and margin
    private JPanel createPanelWithText(String text, int top, int bottom) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(top, 20, bottom, 20));
        return panel;
    }

    public void setBet(int moneyOnTable) {
        this.moneyOnTable = moneyOnTable;
    }

    private void initializeTopPanel() {
        topPanel = createPanelWithText("Dealer's Hand:", 100, 10);

        // Create a panel for the dealer's hand text
        JPanel dealerHandTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel dealerHandTextLabel = new JLabel("Dealer's Hand:");
        dealerHandTextPanel.add(dealerHandTextLabel);
        topPanel.add(dealerHandTextPanel, BorderLayout.NORTH);

        // Create a panel for the card images
        JPanel cardImagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel firstCardLabel = generateImage(getImagePath(dealerHand.get(0)));
        JLabel backCardLabel = generateImage("cards/back.png");
        cardImagesPanel.add(firstCardLabel);
        cardImagesPanel.add(backCardLabel);
        topPanel.add(cardImagesPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
    }

    private void initializeMiddlePanel() {
        middlePanel = createPanelWithText("Total Bet: $" + moneyOnTable, 20, 20);
        add(middlePanel, BorderLayout.CENTER);
    }

    private void initializeBottomPanel() {
        bottomPanel = createPanelWithText("User's Hand:", 10, 100);

        // Create a panel for the dealer's hand text
        JPanel userHandTextPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel userHandTextLabel = new JLabel("User's Hand:");
        userHandTextPanel.add(userHandTextLabel);
        bottomPanel.add(userHandTextPanel, BorderLayout.NORTH);

        // Create a panel for the card images
        JPanel cardImagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (int i = 0; i < userHand.size(); i++) {
            cardImagesPanel.add(generateImage(getImagePath(userHand.get(i))));
        }
        bottomPanel.add(cardImagesPanel, BorderLayout.CENTER);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton hitBtn = new JButton("HIT");
        hitBtn.addActionListener(e -> playHit());
        JButton standBtn = new JButton("STAND");
        JButton doubleBtn = new JButton("DOUBLE");
        buttonPanel.add(hitBtn);
        buttonPanel.add(standBtn);
        buttonPanel.add(doubleBtn);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        revalidate();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void distributeCards() {
        Cards userCard1 = decks.getRandomCard();
        Cards userCard2 = decks.getRandomCard();
        Cards dealerCard1 = decks.getRandomCard();
        Cards dealerCard2 = decks.getRandomCard();

        userHand.add(userCard1);
        userHand.add(userCard2);
        dealerHand.add(dealerCard1);
        dealerHand.add(dealerCard2);
    }

    public void playHit() {
        userHand.add(decks.getRandomCard());
        initializeBottomPanel();
    }

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
}
