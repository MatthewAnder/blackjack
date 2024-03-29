package ui;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        new Terminal();
        SwingUtilities.invokeLater(() -> new HomeGUI());
    }
}
