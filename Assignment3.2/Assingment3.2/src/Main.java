import java.util.Scanner;
/**
 * A program to play Tic Tac Toe
 *
 * @author Eric Wallace
 *
 */
public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println(game);

        while (!game.isGameOver()) {
            TicTacToe.Cell player = game.isXTurn() ? TicTacToe.Cell.X : TicTacToe.Cell.O;
            System.out.println(player + " player's turn. Enter row and column (1-3):");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            try {
                game.makeMove(row, col);
                System.out.println(game);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }

        if (game.hasWinner()) {
            TicTacToe.Cell winner = game.isXTurn() ? TicTacToe.Cell.O : TicTacToe.Cell.X;
            System.out.println(winner + " player wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}