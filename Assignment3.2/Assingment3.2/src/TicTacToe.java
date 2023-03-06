import java.util.Arrays;

/**
 * Building the TicTacToe class.
 * @author Eric Wallace
 */
public class TicTacToe {
    enum Cell { X, O, EMPTY };
    private Cell[][] board;
    private boolean xTurn;

    public TicTacToe() {
        board = new Cell[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
        xTurn = true;
    }

    public boolean isXTurn() {
        return xTurn;
    }

    public void makeMove(int row, int col) {
        if (board[row][col] != Cell.EMPTY) {
            throw new IllegalArgumentException("Cell is not empty");
        }
        board[row][col] = xTurn ? Cell.X : Cell.O;
        xTurn = !xTurn;
    }

    public boolean isGameOver() {
        return hasWinner() || isBoardFull();
    }

    boolean hasWinner() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != Cell.EMPTY && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != Cell.EMPTY && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        if (board[0][0] != Cell.EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != Cell.EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                switch (board[row][col]) {
                    case X:
                        sb.append("X");
                        break;
                    case O:
                        sb.append("O");
                        break;
                    default:
                        sb.append("-");
                        break;
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
