import java.util.Random;
import java.util.Scanner;

public class UC6 {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        int row, col;

        if (isHumanTurn) {
            // Human move
            while (true) {
                int slot = getUserSlot();
                int[] pos = getBoardPosition(slot);
                row = pos[0];
                col = pos[1];

                if (isValidMove(row, col)) {
                    placeMove(row, col, humanSymbol);
                    break;
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }
            System.out.println("Human played:");
        } else {
            // Computer move (random)
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
            System.out.println("Computer played:");
        }

        printBoard();
    }

    static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

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

    // UC2
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

    static void displayTossResult() {
        if (isHumanTurn) {
            System.out.println("Human won the toss and plays first.");
        } else {
            System.out.println("Computer won the toss and plays first.");
        }

        System.out.println("Human: " + humanSymbol);
        System.out.println("Computer: " + computerSymbol);
    }

    // UC3
    static int getUserSlot() {
        int slot;
        while (true) {
            System.out.print("Enter slot (1-9): ");
            slot = sc.nextInt();

            if (slot >= 1 && slot <= 9) return slot;
            else System.out.println("Invalid input!");
        }
    }

    // UC4
    static int[] getBoardPosition(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

    // UC5
    static boolean isValidMove(int row, int col) {
        return board[row][col] == '-';
    }

    // UC6
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }
}