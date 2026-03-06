public class TicTacToe {
    public static void main(String[] args) {
        Board board = new Board();
        
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Squares are numbered 1-9 as shown below:");
        board.display();
        
        System.out.println("Demonstrating moves...");
        System.out.println("Player X moves to position 5:");
        board.makeMove(5, 'X');
        board.display();
        
        System.out.println("Player O moves to position 1:");
        board.makeMove(1, 'O');
        board.display();
        
        System.out.println("Player X moves to position 9:");
        board.makeMove(9, 'X');
        board.display();
        
        System.out.println("Board demonstration complete.");
    }
}
