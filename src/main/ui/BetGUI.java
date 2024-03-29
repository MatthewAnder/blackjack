package ui;

import model.History;
import model.User;

import javax.swing.*;
import java.awt.*;

public class BetGUI extends JPanel {
    private JPanel pages;
    private CardLayout pagesLayout;
    private int moneyOnTable;
    private History history;
    private User user;

    private JTextField moneyIn;

    public BetGUI(JPanel pages, CardLayout pagesLayout, User user, History history) {
        super();
        this.pages = pages;
        this.pagesLayout = pagesLayout;
        moneyOnTable = 0;
        this.history = history;
        this.user = user;

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
        JLabel textLabel = new JLabel("Bet money: $ ");
        add(textLabel);

        moneyIn = new JTextField(10);

        constraints.fill = GridBagConstraints.BOTH;
        add(moneyIn, constraints);
        initializeButtons(constraints);
    }

    private void initializeButtons(GridBagConstraints constraints) {
        JButton betBtn = new JButton("Bet");
        betBtn.addActionListener(e -> startGame());
        JButton backBtn = new JButton("Go Back");
        backBtn.addActionListener(e -> pagesLayout.show(pages, HomeGUI.HOME_PANEL));

        addBtn(constraints, betBtn, 1);
        addBtn(constraints, backBtn, 2);
    }

    private void startGame() {
        boolean isNumber = true;
        try {
            moneyOnTable = Integer.parseInt(moneyIn.getText().trim());
            user.takeMoney(moneyOnTable);
            pages.add(new TableGUI(pages, pagesLayout, moneyOnTable, history, user), HomeGUI.TABLE_PANEL);
        } catch (NumberFormatException e) {
            System.out.println("Not a number!");
            isNumber = false;
        } catch (Exception e) {
            System.out.println("Something is not working");
            isNumber = false;
        }

        if (isNumber && moneyOnTable > 0 && moneyOnTable <= user.getMoney()) {
            pagesLayout.show(pages, HomeGUI.TABLE_PANEL);
        } else {
            JOptionPane.showMessageDialog(null, "Something is not right!");
        }
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridy = padY;
        add(btn, constraints);
    }
}
