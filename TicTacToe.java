/**
 * Tic Tac Toe Game
 * A text-based implementation of the classic Tic Tac Toe game for two players.
 */
public class TicTacToe {
    // Board representation: array of 9 squares (indices 0-8)
    // Values: ' ' for empty, 'X' for player 1, 'O' for player 2
    private char[] board;
    
    // Constants for player symbols
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final char EMPTY = ' ';
    
    /**
     * Constructor - initializes an empty board
     */
    public TicTacToe() {
        board = new char[9];
        initializeBoard();
    }
    
    /**
     * Initialize all squares to empty
     */
    private void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = EMPTY;
        }
    }
    
    /**
     * Get the value at a specific square
     * @param position Square number (1-9 for user-facing, converted to 0-8 internally)
     * @return The character at that position (X, O, or empty)
     */
    public char getSquare(int position) {
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Position must be between 1 and 9");
        }
        return board[position - 1];
    }
    
    /**
     * Set a value at a specific square
     * @param position Square number (1-9)
     * @param player The player's symbol (X or O)
     */
    public void setSquare(int position, char player) {
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Position must be between 1 and 9");
        }
        if (player != PLAYER_X && player != PLAYER_O && player != EMPTY) {
            throw new IllegalArgumentException("Player must be X, O, or empty");
        }
        board[position - 1] = player;
    }
    
    /**
     * Check if a square is empty
     * @param position Square number (1-9)
     * @return true if the square is empty, false otherwise
     */
    public boolean isSquareEmpty(int position) {
        if (position < 1 || position > 9) {
            return false;
        }
        return board[position - 1] == EMPTY;
    }
    
    /**
     * Reset the board to empty state
     */
    public void resetBoard() {
        initializeBoard();
    }
    
    /**
     * Get a copy of the current board state
     * @return A copy of the board array
     */
    public char[] getBoardCopy() {
        char[] copy = new char[9];
        System.arraycopy(board, 0, copy, 0, 9);
        return copy;
    }
}
