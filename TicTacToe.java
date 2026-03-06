import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        char currentPlayer = 'X';
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Players take turns entering a square number (1-9).");
        
        while (true) {
            board.display();
            System.out.print("Player " + currentPlayer + ", enter your move (1-9): ");
            
            String input = scanner.nextLine().trim();
            int move;
            
            try {
                move = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-9.");
                continue;
            }
            
            if (!board.makeMove(move, currentPlayer)) {
                System.out.println("Invalid move. Square is either taken or out of range.");
                continue;
            }
            
            char winner = board.checkWinner();
            if (winner != ' ') {
                board.display();
                System.out.println(winner + " wins!");
                break;
            }
            
            if (board.isFull()) {
                board.display();
                System.out.println("CAT");
                break;
            }
            
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        
        scanner.close();
    }
}
