public class Board {
    private char[] cells;
    
    public Board() {
        cells = new char[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = ' ';
        }
    }
    
    public boolean isValidMove(int position) {
        if (position < 1 || position > 9) {
            return false;
        }
        return cells[position - 1] == ' ';
    }
    
    public boolean makeMove(int position, char player) {
        if (!isValidMove(position)) {
            return false;
        }
        cells[position - 1] = player;
        return true;
    }
    
    public char getCell(int position) {
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Position must be between 1 and 9");
        }
        return cells[position - 1];
    }
    
    public boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (cells[i] == ' ') {
                return false;
            }
        }
        return true;
    }
    
    public void display() {
        System.out.println();
        for (int row = 0; row < 3; row++) {
            System.out.print(" ");
            for (int col = 0; col < 3; col++) {
                int index = row * 3 + col;
                char cell = cells[index];
                if (cell == ' ') {
                    System.out.print(index + 1);
                } else {
                    System.out.print(cell);
                }
                if (col < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }
}
