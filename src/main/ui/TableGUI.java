package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TableGUI extends JPanel {
    JPanel pages;
    CardLayout pagesLayout;

    public TableGUI(JPanel pages, CardLayout pagesLayout) {
        super();
        this.pages = pages;
        this.pagesLayout = pagesLayout;

        initializeGraphics();

        JLabel title = new JLabel();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        title.setText("<html><h1>Welcome to Jack n' Co</h1></html>");

        initializeConstraints(constraints);
        add(title);

        initializeButtons(constraints);
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
        setLayout(new BorderLayout());
        setVisible(true);
    }

    private void initializeButtons(GridBagConstraints constraints) {
        JButton startBtn = new JButton("Go Back");
        startBtn.addActionListener(e -> pagesLayout.show(pages, "Card with Home"));

        addBtn(constraints, startBtn, 1);
    }

    // MODIFIES:
    // EFFECTS:
    private void addBtn(GridBagConstraints constraints, JButton btn, int padY) {
        constraints.gridx = 0;
        constraints.gridy = padY;
        add(btn, constraints);
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
