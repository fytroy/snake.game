// src/main/java/com/yourcompany/snakegame/Main.java
package com.yourcompany.snakegame;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        // Ensure that GUI updates are done on the Event Dispatch Thread
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            GamePanel gamePanel = new GamePanel(); // We'll create this next
            frame.add(gamePanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false); // Prevent resizing issues
            frame.pack(); // Sizes the frame to fit the preferred size of its subcomponents
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);

            // Request focus for the game panel to receive key events
            gamePanel.requestFocusInWindow();
        });
    }
}