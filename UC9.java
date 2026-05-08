import java.util.Random;
import java.util.Scanner;

public class UC9 {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        initializeBoard();
        tossAndAssignSymbols();
        displayTossResult();

        boolean gameOver = false;

        while (!gameOver) {

            printBoard();

            if (isHumanTurn) {

                System.out.println("Human Turn");
                humanMove();

                if (checkWinner(humanSymbol)) {
                    printBoard();
                    System.out.println("Human Wins!");
                    gameOver = true;
                }

            } else {

                System.out.println("Computer Turn");
                computerMove();

                if (checkWinner(computerSymbol)) {
                    printBoard();
                    System.out.println("Computer Wins!");
                    gameOver = true;
                }
            }

            if (!gameOver && checkDraw()) {
                printBoard();
                System.out.println("Game Draw!");
                gameOver = true;
            }

            isHumanTurn = !isHumanTurn;
        }
    }

    // Initialize Board
    static void initializeBoard() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                board[i][j] = '-';
            }
        }
    }

    // Print Board
    static void printBoard() {

        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {

            System.out.print("| ");

            for (int j = 0; j < 3; j++) {

                System.out.print(board[i][j] + " | ");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    // Toss
    static void tossAndAssignSymbols() {

        int toss = rand.nextInt(2);

        if (toss == 0) {

            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';

        } else {

            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    // Display Toss Result
    static void displayTossResult() {

        if (isHumanTurn) {

            System.out.println("Human won toss and plays first.");

        } else {

            System.out.println("Computer won toss and plays first.");
        }

        System.out.println("Human Symbol: " + humanSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
    }

    // Human Move
    static void humanMove() {

        int row, col;

        while (true) {

            int slot = getUserSlot();

            int[] pos = getBoardPosition(slot);

            row = pos[0];
            col = pos[1];

            if (isValidMove(row, col)) {

                placeMove(row, col, humanSymbol);
                break;

            } else {

                System.out.println("Invalid Move! Try Again.");
            }
        }
    }

    // Computer Move
    static void computerMove() {

        int row, col;

        while (true) {

            int slot = rand.nextInt(9) + 1;

            int[] pos = getBoardPosition(slot);

            row = pos[0];
            col = pos[1];

            if (isValidMove(row, col)) {

                placeMove(row, col, computerSymbol);
                break;
            }
        }
    }

    // Get User Slot
    static int getUserSlot() {

        int slot;

        while (true) {

            System.out.print("Enter slot (1-9): ");
            slot = sc.nextInt();

            if (slot >= 1 && slot <= 9) {

                return slot;

            } else {

                System.out.println("Invalid Input!");
            }
        }
    }

    // Convert Slot to Position
    static int[] getBoardPosition(int slot) {

        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;

        return new int[]{row, col};
    }

    // Check Valid Move
    static boolean isValidMove(int row, int col) {

        return board[row][col] == '-';
    }

    // Place Move
    static void placeMove(int row, int col, char symbol) {

        board[row][col] = symbol;
    }

    // UC9 - Check Winner
    static boolean checkWinner(char symbol) {

        // Check Rows
        for (int i = 0; i < 3; i++) {

            if (board[i][0] == symbol &&
                board[i][1] == symbol &&
                board[i][2] == symbol) {

                return true;
            }
        }

        // Check Columns
        for (int j = 0; j < 3; j++) {

            if (board[0][j] == symbol &&
                board[1][j] == symbol &&
                board[2][j] == symbol) {

                return true;
            }
        }

        // Check Main Diagonal
        if (board[0][0] == symbol &&
            board[1][1] == symbol &&
            board[2][2] == symbol) {

            return true;
        }

        // Check Opposite Diagonal
        if (board[0][2] == symbol &&
            board[1][1] == symbol &&
            board[2][0] == symbol) {

            return true;
        }

        return false;
    }

    // Check Draw
    static boolean checkDraw() {

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