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
}
