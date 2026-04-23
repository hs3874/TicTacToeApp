import java.util.Random;
import java.util.Scanner;

public class UC5 {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static char[][] board = new char[3][3];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        int row, col;

        
        
        while (true) {
            int slot = getUserSlot(); 
            int[] position = getBoardPosition(slot); 
            row = position[0];
            col = position[1];

            if (isValidMove(row, col)) { 
                break;
            } else {
                System.out.println("Invalid move! Cell already occupied or out of range. Try again.");
            }
        }

        
        
        board[row][col] = humanSymbol;
        printBoard();
    }

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

   
   
    static void tossAndAssignSymbols() {
        Random rand = new Random();
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
            System.out.println("Human won the toss and will play first.");
        } else {
            System.out.println("Computer won the toss and will play first.");
        }

        System.out.println("Human Symbol: " + humanSymbol);
        System.out.println("Computer Symbol: " + computerSymbol);
    }

   
   
    static int getUserSlot() {
        int slot;
        while (true) {
            System.out.print("Enter a slot number (1-9): ");
            slot = sc.nextInt();

            if (slot >= 1 && slot <= 9) {
                return slot;
            } else {
                System.out.println("Invalid input! Enter between 1 and 9.");
            }
        }
    }

    
    
    static int[] getBoardPosition(int slot) {
        int row = (slot - 1) / 3;
        int col = (slot - 1) % 3;
        return new int[]{row, col};
    }

   
   
    static boolean isValidMove(int row, int col) {
       
       
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

      
      
       if (board[row][col] != '-') {
            return false;
        }

        return true;
    }
}