package ui;

import model.History;

import javax.swing.*;
import java.awt.*;

public class HistoryGUI extends JPanel {
    JPanel pages;
    CardLayout pagesLayout;
    History history;

    public HistoryGUI(JPanel pages, CardLayout pagesLayout, History history) {
        super();
        this.pages = pages;
        this.pagesLayout = pagesLayout;
        this.history = history;

        initializeGraphics();

        JLabel title = new JLabel();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        title.setText("<html><h1>HISTORY</h1></html>");

        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 1000;
        constraints.gridy = 0;
        add(title);

        initializeButtons(constraints);
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
        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(e -> showHistory(constraints));

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

    public void showHistory(GridBagConstraints constraints) {
        pagesLayout.show(pages, HomeGUI.HISTORY_PANEL);
        if (history.getHistory() != null) {
            JLabel historyTitle = new JLabel("History: ");
            add(historyTitle);


            for (int i = 0; i < history.getHistory().size(); i++) {
                JLabel historyText = new JLabel();
                constraints.gridy = 3 + i;
                historyText.setText("<html>" + history.getHistory().get(i) + "<br/></html>");
                add(historyText, constraints);
            }

        } else {
            System.out.println("No History!");
        }
    }
}
