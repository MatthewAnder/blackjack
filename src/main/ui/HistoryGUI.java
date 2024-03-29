package ui;

import model.Cards;
import model.History;
import model.Game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class HistoryGUI extends JPanel {
    private JPanel pages;
    private CardLayout pagesLayout;
    private History history;
    private StringBuilder historyText;

    public HistoryGUI(JPanel pages, CardLayout pagesLayout, History history) {
        super();
        this.pages = pages;
        this.pagesLayout = pagesLayout;
        this.history = history;

        initializeGraphics();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        initializeHeader(constraints);
        initializeButtons(constraints);
    }

    private void initializeHeader(GridBagConstraints c) {
        JLabel title = new JLabel();
        title.setText("<html><h1>HISTORY</h1></html>");

        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 1000;
        c.gridy = 0;
        add(title);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setVisible(true);
    }

    private void initializeButtons(GridBagConstraints constraints) {
        JButton backBtn = new JButton("Go Back");
        backBtn.addActionListener(e -> pagesLayout.show(pages, "Card with Home"));
        JButton loadBtn = new JButton("Refresh");
        loadBtn.addActionListener(e -> showHistory(constraints, loadBtn));

        addBtn(constraints, loadBtn, 1);
        addBtn(constraints, backBtn, 2);
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridx = 0;
        constraints.gridy = padY;
        add(btn, constraints);
    }

    public void showHistory(GridBagConstraints constraints, JButton btn) {
        if (history.getHistory() != null) {
            JLabel historyTitle = new JLabel("History: ");
            add(historyTitle, constraints);
            historyText = new StringBuilder();
            historyText.append("<html>");
            generateContent();
            historyText.append("</html>");

            constraints.gridy = 4;
            JLabel content = new JLabel(historyText.toString());
            JScrollPane scroller = new JScrollPane(content,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroller.setPreferredSize(new Dimension(600,400));
            scroller.getViewport().setBackground(Color.WHITE);
            add(scroller, constraints);
        } else {
            System.out.println("No History!");
        }
        revalidate();
    }

    private void generateContent() {
        for (int i = 0; i < history.getHistory().size(); i++) {
            Game game = history.getHistory().get(i);
            historyText.append("Bet = " + game.getMoneyOnTable() + "<br />");
            historyText.append("Status = " + ((game.getStatusOfGame()) ? "win" : "lose") + "<br />");
            generateUserCards(game);
            historyText.append("<br />");
            generateDealerCards(game);
            historyText.append("<br />");
        }
    }

    private void generateUserCards(Game game) {
        historyText.append("User hand = <br />");

        for (int j = 0; j < game.getUserHands().size(); j++) {
            Cards userHand = game.getUserHands().get(j);
            String imagePath = "cards/"
                    + userHand.getRank().toLowerCase()
                    + "_of_"
                    + userHand.getSuits().toLowerCase()
                    + ".png";
            String fileName = getClass().getClassLoader().getResource(imagePath).toString();
            historyText.append("<img src ='" + fileName + "' width='100' height='150' />");
        }
    }

    private void generateDealerCards(Game game) {

        historyText.append("Dealer hand = <br />");

        for (int j = 0; j < game.getDealerHands().size(); j++) {
            Cards dealerHand = game.getDealerHands().get(j);
            String imagePath = "cards/"
                    + dealerHand.getRank().toLowerCase()
                    + "_of_"
                    + dealerHand.getSuits().toLowerCase()
                    + ".png";
            String fileName = getClass().getClassLoader().getResource(imagePath).toString();
            historyText.append("<img src ='"
                    + fileName
                    + "' width='100' height='150' />");
        }
    }
}
