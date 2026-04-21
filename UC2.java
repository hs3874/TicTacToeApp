import java.util.Scanner;

public class UC2 {

    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initializeBoard();

        while (true) {
            printBoard();
            playerMove(sc);

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isDraw()) {
                printBoard();
                System.out.println("Game is a draw!");
                break;
            }

            switchPlayer();
        }

        sc.close();
    }

    // Initialize board
    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print board
    static void printBoard() {
        System.out.println("\n-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Player move
    static void playerMove(Scanner sc) {
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter row (0-2) and column (0-2): ");
            row = sc.nextInt();
            col = sc.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // Switch player
    static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check win
    static boolean checkWin() {
        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        // Diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    // Check draw
    static boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}