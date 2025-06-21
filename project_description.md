# Project Description: Snake Game

This document provides a technical overview of the Snake Game project.

## Project Structure

The project is a simple Java Swing application with the following main components:

*   `src/`
    *   `Main.java`: This is the entry point of the application. Its primary responsibility is to set up the main game window (`JFrame`) and initialize the `GamePanel`.
    *   `GamePanel.java`: This class extends `JPanel` and contains the core game logic. It handles:
        *   Game state (snake position, food position, score, running status).
        *   Game loop and timing (using `javax.swing.Timer`).
        *   Rendering the game (drawing the snake, food, grid, score, game over screen).
        *   Handling keyboard input for snake movement and game restart.
        *   Collision detection (snake with food, snake with walls, snake with itself).

## Key Classes and Responsibilities

*   **`Main`**:
    *   Initializes and displays the main `JFrame`.
    *   Creates an instance of `GamePanel` and adds it to the frame.
    *   Ensures GUI updates are handled on the Event Dispatch Thread using `EventQueue.invokeLater()`.

*   **`GamePanel`**:
    *   Implements `ActionListener` to respond to timer events for the game loop.
    *   `startGame()`: Initializes or resets the game state (snake, food, score, timer).
    *   `newFood()`: Randomly places a new food item on the game grid.
    *   `move()`: Updates the snake's position based on the current direction.
    *   `checkFoodCollision()`: Checks if the snake's head has reached the food; if so, increases score, snake length, and spawns new food.
    *   `checkCollisions()`: Checks for game-ending collisions (snake with walls or itself).
    *   `paintComponent(Graphics g)`: Overridden from `JPanel` to draw the game elements.
    *   `draw(Graphics g)`: Helper method called by `paintComponent` to render the current game state.
    *   `gameOver(Graphics g)`: Displays the "Game Over" message and final score.
    *   **`MyKeyAdapter` (inner class)**: Extends `KeyAdapter` to handle user input (arrow keys for direction, 'R' for restart).

## Potential Future Enhancements

*   **Difficulty Levels:** Implement different speed settings or other challenges.
*   **Sound Effects:** Add sounds for eating food, game over, etc.
*   **High Score Persistence:** Save and display high scores (e.g., to a file).
*   **Different Food Types:** Introduce food items with different point values or effects.
*   **Obstacles:** Add static obstacles on the game board.
*   **Improved Visuals/Themes:** Allow for different color schemes or sprite-based graphics.
