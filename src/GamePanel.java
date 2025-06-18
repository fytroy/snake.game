// src/main/java/com/yourcompany/snakegame/GamePanel.java
package com.yourcompany.snakegame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; // Size of each snake body part/food
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 100; // Game speed (milliseconds)

    final ArrayList<Point> snake;
    Point food;
    int snakeLength;
    char direction; // 'U', 'D', 'L', 'R'
    boolean running;
    Timer timer;
    Random random;
    int score;

    public GamePanel() {
        random = new Random();
        snake = new ArrayList<>();
        direction = 'R'; // Initial direction
        running = false;
        score = 0;

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true); // Allow this panel to receive key events
        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame() {
        newFood();
        // Start with a small snake
        snake.clear();
        snake.add(new Point(0, 0)); // Initial head position
        snakeLength = 1;

        running = true;
        timer = new Timer(DELAY, this); // 'this' because GamePanel implements ActionListener
        timer.start();
    }

    public void newFood() {
        int x = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        int y = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        food = new Point(x, y);
    }

    public void move() {
        // Create a new head based on current head and direction
        Point head = snake.get(0);
        Point newHead = new Point(head.x, head.y);

        switch (direction) {
            case 'U':
                newHead.y -= UNIT_SIZE;
                break;
            case 'D':
                newHead.y += UNIT_SIZE;
                break;
            case 'L':
                newHead.x -= UNIT_SIZE;
                break;
            case 'R':
                newHead.x += UNIT_SIZE;
                break;
        }

        // Add the new head
        snake.add(0, newHead);

        // If food not eaten, remove the tail
        if (!checkFoodCollision()) {
            snake.remove(snake.size() - 1);
        }
    }

    public boolean checkFoodCollision() {
        if (snake.get(0).equals(food)) {
            score++;
            snakeLength++; // Grow the snake
            newFood();
            return true;
        }
        return false;
    }

    public void checkCollisions() {
        Point head = snake.get(0);

        // Check self-collision
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }

        // Check wall collision
        if (head.x < 0 || head.x >= SCREEN_WIDTH || head.y < 0 || head.y >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    // This method is called repeatedly by the Timer
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollisions();
        }
        repaint(); // Calls paintComponent
    }

    // This method is called to draw components
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw grid (optional, helpful for debugging)
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

            // Draw Food
            g.setColor(Color.RED);
            g.fillOval(food.x, food.y, UNIT_SIZE, UNIT_SIZE);

            // Draw Snake
            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN); // Head
                } else {
                    g.setColor(new Color(45, 180, 0)); // Body
                }
                g.fillRect(snake.get(i).x, snake.get(i).y, UNIT_SIZE, UNIT_SIZE);
            }

            // Draw Score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            g.drawString("Score: " + score, (SCREEN_WIDTH - g.getFontMetrics().stringWidth("Score: " + score)) / 2, g.getFont().getSize());

        } else {
            gameOver(g);
        }
    }

    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        String gameOverText = "Game Over!";
        g.drawString(gameOverText, (SCREEN_WIDTH - g.getFontMetrics().stringWidth(gameOverText)) / 2, SCREEN_HEIGHT / 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        String scoreText = "Score: " + score;
        g.drawString(scoreText, (SCREEN_WIDTH - g.getFontMetrics().stringWidth(scoreText)) / 2, SCREEN_HEIGHT / 2 + 50);

        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        String restartText = "Press 'R' to Restart";
        g.drawString(restartText, (SCREEN_WIDTH - g.getFontMetrics().stringWidth(restartText)) / 2, SCREEN_HEIGHT / 2 + 100);
    }

    // Inner class for handling keyboard input
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') { // Prevent 180-degree turn
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_R: // Restart game
                    if (!running) {
                        startGame();
                    }
                    break;
            }
        }
    }
}