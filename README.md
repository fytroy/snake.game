# Snake Game

A classic Snake game where the player controls a snake to eat food and grow longer, while trying to avoid collisions with the walls and its own body.

## How to Play

*   **Objective:** Eat the red food pellets to grow your snake and increase your score. The game ends if the snake hits a wall or runs into its own body.
*   **Controls:**
    *   **Arrow Up:** Move the snake up.
    *   **Arrow Down:** Move the snake down.
    *   **Arrow Left:** Move the snake left.
    *   **Arrow Right:** Move the snake right.
    *   **R:** Restart the game after a "Game Over".

## How to Run

This is a Java Swing application.

1.  **Compile the code:**
    Open a terminal or command prompt in the root directory of the project.
    ```bash
    javac -d out src/*.java
    ```
    (This command compiles the .java files from the `src` directory and places the .class files into an `out` directory. If the `out` directory doesn't exist, `javac` will usually create it. If not, you might need to create it manually: `mkdir out`)

2.  **Run the game:**
    ```bash
    java -cp out com.yourcompany.snakegame.Main
    ```
    (This command runs the game, looking for the compiled classes in the `out` directory.)
