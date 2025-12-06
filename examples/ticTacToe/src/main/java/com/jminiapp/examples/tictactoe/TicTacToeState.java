package com.jminiapp.examples.tictactoe;

public class TicTacToeState {

    private char[] board;
    private char currentPlayer;
    private boolean gameOver;
    private String winner;

    public TicTacToeState() {
        reset();
    }

    public void reset() {
        board = new char[]{
                ' ', ' ', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        };
        currentPlayer = 'X';
        gameOver = false;
        winner = null;
    }

    public char[] getBoard() { return board; }
    public char getCurrentPlayer() { return currentPlayer; }
    public boolean isGameOver() { return gameOver; }
    public String getWinner() { return winner; }

    public void setBoard(char[] b) { this.board = b; }
    public void setCurrentPlayer(char p) { this.currentPlayer = p; }
    public void setGameOver(boolean g) { this.gameOver = g; }
    public void setWinner(String w) { this.winner = w; }

    /**
     * Attempts to place currentPlayer at given index.
     */
    public boolean playMove(int index) {
        if (index < 0 || index > 8) return false;
        if (board[index] != ' ' || gameOver) return false;

        board[index] = currentPlayer;

        checkWinner();

        if (!gameOver) switchPlayer();

        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void checkWinner() {
        int[][] lines = {
                {0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };

        for (int[] line : lines) {
            char a = board[line[0]];
            char b = board[line[1]];
            char c = board[line[2]];

            if (a == b && b == c && a != ' ') {
                gameOver = true;
                winner = String.valueOf(a);
                return;
            }
        }

        boolean full = true;
        for (char cell : board) {
            if (cell == ' ') {
                full = false;
                break;
            }
        }

        if (full) {
            gameOver = true;
            winner = "Draw";
        }
    }
}
