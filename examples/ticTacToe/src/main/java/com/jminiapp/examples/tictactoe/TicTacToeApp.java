package com.jminiapp.examples.tictactoe;

import com.jminiapp.core.api.JMiniApp;
import com.jminiapp.core.api.JMiniAppConfig;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TicTacToeApp extends JMiniApp {

    private Scanner scanner;
    private TicTacToeState state;
    private boolean running;

    public TicTacToeApp(JMiniAppConfig config) {
        super(config);
    }

    @Override
    protected void initialize() {
        scanner = new Scanner(System.in);
        running = true;

        List<TicTacToeState> data = context.getData();

        if (data != null && !data.isEmpty()) {
            state = data.get(0);
            System.out.println("Loaded existing game from JSON.");
        } else {
            state = new TicTacToeState();
            System.out.println("Starting new TicTacToe game!");
        }
    }

    @Override
    protected void run() {
        while (running) {
            displayMenu();
            handleInput();
        }
    }

    @Override
    protected void shutdown() {
        context.setData(List.of(state));
        System.out.println("Game saved. Goodbye!");
    }

    private void displayMenu() {
        System.out.println("\n=== TicTacToe ===");
        printBoard();

        if (state.isGameOver()) {
            System.out.println("Game Over! Winner: " + state.getWinner());
        }

        System.out.println("\n1. Play (Player vs Player)");
        System.out.println("2. Play vs CPU");
        System.out.println("3. Reset Game");
        System.out.println("4. Save Game (Export JSON)");
        System.out.println("5. Load Game (Import JSON)");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleInput() {
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1": playPVPMatch(); break;
            case "2": playCPUMatch(); break;
            case "3":
                state.reset();
                System.out.println("Game reset!");
                break;
            case "4": exportJson(); break;
            case "5": importJson(); break;
            case "6": running = false; break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void playPVPMatch() {
        while (true) {
            if (state.isGameOver()) {
                System.out.println("\n=== Game Over! Winner: " + state.getWinner() + " ===");
                printBoard();
                break;
            }

            System.out.println("\n--- Player " + state.getCurrentPlayer() + "'s turn ---");
            printBoard();
            System.out.print("Enter cell (1-9): ");

            int cell;
            try {
                cell = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            if (!state.playMove(cell)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (state.isGameOver()) {
                System.out.println("\n=== Game Over! Winner: " + state.getWinner() + " ===");
                printBoard();
                break;
            }

            if (!askContinue()) {
                break;
            }
        }
    }

    private void playCPUMatch() {
        while (true) {
            if (state.isGameOver()) {
                System.out.println("\n=== Game Over! Winner: " + state.getWinner() + " ===");
                printBoard();
                break;
            }

            // Human player (X)
            System.out.println("\n--- Your turn (X) ---");
            printBoard();
            System.out.print("Enter cell (1-9): ");

            int cell;
            try {
                cell = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            if (!state.playMove(cell)) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (state.isGameOver()) {
                System.out.println("\n=== Game Over! Winner: " + state.getWinner() + " ===");
                printBoard();
                break;
            }

            // CPU player (O)
            System.out.println("\n--- CPU's turn (O) ---");
            cpuMove();
            printBoard();

            if (state.isGameOver()) {
                System.out.println("\n=== Game Over! Winner: " + state.getWinner() + " ===");
                break;
            }

            if (!askContinue()) {
                break;
            }
        }
    }

    private boolean askContinue() {
        System.out.print("\nContinue playing? (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("n") || answer.equals("no")) {
            System.out.print("Save game before pausing? (y/n): ");
            String saveAnswer = scanner.nextLine().trim().toLowerCase();

            if (saveAnswer.equals("y") || saveAnswer.equals("yes")) {
                exportJson();
            }
            return false;
        }
        return true;
    }

    private void cpuMove() {
        char[] board = state.getBoard();

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                state.playMove(i);
                System.out.println("CPU played position " + (i + 1));
                return;
            }
        }
    }

    private void printBoard() {
        char[] b = state.getBoard();
        System.out.println(
                "\n " + b[0] + " | " + b[1] + " | " + b[2] +
                "\n---+---+---" +
                "\n " + b[3] + " | " + b[4] + " | " + b[5] +
                "\n---+---+---" +
                "\n " + b[6] + " | " + b[7] + " | " + b[8]
        );
    }

    private void exportJson() {
        try {
            context.setData(List.of(state));

            System.out.print("Enter filename to export (e.g., tictactoe.json): ");
            String filename = scanner.nextLine().trim();

            if (filename.isEmpty()) {
                System.out.println("Filename cannot be empty.");
                return;
            }

            // Ensure .json extension
            if (!filename.endsWith(".json")) {
                filename += ".json";
            }

            context.exportData(filename, "json");

            // Get the absolute path for user feedback
            String absolutePath = System.getProperty("user.dir") + "/" + filename;
            System.out.println("TicTacToe state exported successfully to: " + absolutePath);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    private void importJson() {
        try {
            System.out.print("Enter filename to import (e.g., tictactoe.json): ");
            String filename = scanner.nextLine().trim();

            if (filename.isEmpty()) {
                System.out.println("Filename cannot be empty.");
                return;
            }

            // Ensure .json extension
            if (!filename.endsWith(".json")) {
                filename += ".json";
            }

            context.importData(filename, "json");
            List<TicTacToeState> data = context.getData();

            if (data != null && !data.isEmpty()) {
                state = data.get(0);
                System.out.println("TicTacToe state imported successfully!");
                printBoard();
            } else {
                System.out.println("No saved game found.");
            }
        } catch (IOException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}
