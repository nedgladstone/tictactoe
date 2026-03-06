public class Board {
    private char[] squares;

    public Board() {
        squares = new char[9];
        for (int i = 0; i < 9; i++) {
            squares[i] = ' ';
        }
    }

    public void display() {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                if (squares[index] == ' ') {
                    System.out.print(" " + (index + 1) + " ");
                } else {
                    System.out.print(" " + squares[index] + " ");
                }
                if (col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

    public boolean isValidMove(int position) {
        if (position < 1 || position > 9) {
            return false;
        }
        return squares[position - 1] == ' ';
    }

    public boolean makeMove(int position, char player) {
        if (!isValidMove(position)) {
            return false;
        }
        squares[position - 1] = player;
        return true;
    }

    public char getSquare(int position) {
        if (position < 1 || position > 9) {
            return ' ';
        }
        return squares[position - 1];
    }

    public char checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
            {0, 4, 8}, {2, 4, 6}              // diagonals
        };

        for (int[] pattern : winPatterns) {
            char first = squares[pattern[0]];
            if (first != ' ' && 
                first == squares[pattern[1]] && 
                first == squares[pattern[2]]) {
                return first;
            }
        }
        return ' ';
    }

    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (squares[i] == ' ') {
                return false;
            }
        }
        return true;
    }
}
