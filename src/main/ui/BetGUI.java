package ui;

import javax.swing.*;
import java.awt.*;

public class BetGUI extends JPanel {
    JPanel pages;
    CardLayout pagesLayout;

    public BetGUI(JPanel pages, CardLayout pagesLayout) {
        super();
        this.pages = pages;
        this.pagesLayout = pagesLayout;

        initializeGraphics();
        GridBagConstraints constraints = new GridBagConstraints();

        initializeConstraints(constraints);
        initializeContent(constraints);
    }

    private void initializeConstraints(GridBagConstraints c) {
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 1000;
        c.gridy = 0;
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setVisible(true);
        setLayout(new GridBagLayout());
    }

    private void initializeContent(GridBagConstraints constraints) {
        JLabel moneyText = new JLabel("Bet money: $ ");
        add(moneyText);

        JTextArea in = new JTextArea(1, 100);
        in.getText();
        constraints.fill = GridBagConstraints.BOTH;
        add(in, constraints);
        initializeButtons(constraints);
    }

    private void initializeButtons(GridBagConstraints constraints) {
        JButton betBtn = new JButton("Bet");
        betBtn.addActionListener(e -> startGame());
        JButton backBtn = new JButton("Go Back");
        backBtn.addActionListener(e -> pagesLayout.show(pages, "Card with Home"));

        addBtn(constraints, betBtn, 1);
        addBtn(constraints, backBtn, 2);
    }

    private void startGame() {
        pagesLayout.show(pages, HomeGUI.TABLE_PANEL);
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridy = padY;
        add(btn, constraints);
    }
}
