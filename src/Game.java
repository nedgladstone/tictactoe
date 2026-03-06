import java.util.Scanner;

public class Game {
    private Board board;
    private char currentPlayer;
    private Scanner scanner;
    private boolean gameOver;
    private String result;
    
    public Game() {
        board = new Board();
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        gameOver = false;
        result = null;
    }
    
    public void play() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Players take turns entering a number (1-9) to place their mark.");
        
        while (!gameOver) {
            board.display();
            int move = getPlayerMove();
            board.makeMove(move, currentPlayer);
            
            if (checkWin()) {
                board.display();
                result = String.valueOf(currentPlayer);
                gameOver = true;
            } else if (board.isFull()) {
                board.display();
                result = "CAT";
                gameOver = true;
            } else {
                switchPlayer();
            }
        }
        
        displayResult();
    }
    
    private int getPlayerMove() {
        int move = -1;
        boolean validMove = false;
        
        while (!validMove) {
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            
            if (scanner.hasNextInt()) {
                move = scanner.nextInt();
                if (board.isValidMove(move)) {
                    validMove = true;
                } else {
                    System.out.println("Invalid move. That square is already taken or out of range.");
                }
            } else {
                scanner.next();
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
            }
        }
        
        return move;
    }
    
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    public boolean checkWin() {
        int[][] winningCombinations = {
            {1, 2, 3}, // Top row
            {4, 5, 6}, // Middle row
            {7, 8, 9}, // Bottom row
            {1, 4, 7}, // Left column
            {2, 5, 8}, // Middle column
            {3, 6, 9}, // Right column
            {1, 5, 9}, // Diagonal top-left to bottom-right
            {3, 5, 7}  // Diagonal top-right to bottom-left
        };
        
        for (int[] combo : winningCombinations) {
            char a = board.getCell(combo[0]);
            char b = board.getCell(combo[1]);
            char c = board.getCell(combo[2]);
            
            if (a != ' ' && a == b && b == c) {
                return true;
            }
        }
        
        return false;
    }
    
    private void displayResult() {
        if ("CAT".equals(result)) {
            System.out.println("Game over! It's a tie - CAT!");
        } else {
            System.out.println("Game over! Player " + result + " wins!");
        }
    }
    
    public Board getBoard() {
        return board;
    }
    
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getResult() {
        return result;
    }
}
