import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {
    /**
     * Number of main menu entries.
     */
    private static final int MENU_ENTRIES = 2;
    /**
     * Main menu display string.
     */
    private static final String MAIN_MENU = "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /**
     * Menu entry to select a move action.
     */
    public static final int MENU_MOVE = 1;
    /**
     * Menu entry to terminate the program.
     */
    public static final int MENU_EXIT = 2;
    private static Object Path;

    public static boolean have(String[] players, String board_coor_ele) {
        boolean value = false;
        for (int i = 0; i < (players.length - 1); i++) {
            if (board_coor_ele.equals(players[i])) {
                value = true;
            }
        }
        return value;
    }
    // determine whether the element of board_coor is equal to element in players

    public static void displayBoard(String[] players, int dimension) {
        int dim = dimension;
        int len = players.length;

        System.out.print("  ");
        for (int i = 0; i < dim; i++) {
            System.out.print(Character.toString('A' + i));
        }
        System.out.println();
        // print out first 2 rows of the board
        String[][] board_coor = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                board_coor[i][j] = (Character.toString('A' + j) + (i + 1));
            }
        }
        // generate a matrix of board coordinates
        if (dim <= 9) {
            for (int j = 0; j < dim; j++) {
                System.out.print((j + 1) + " ");
                for (int c = 0; c < dim; c++) {
                    if (have(players, board_coor[j][c])) {
                        System.out.print('H');
                    } else if (board_coor[j][c].equals(players[len - 1])) {
                        System.out.print('F');
                    } else {
                        System.out.print(".");
                    }
                }
                System.out.println(" " + (j + 1));
                System.out.println();
            }
        } // when dim <= 9
        else { // when dim > 9
            for (int j = 0; j < dim; j++) {
                if (j < 9) {
                    System.out.print("0" + (j + 1) + " ");
                } // print rows with "0"
                else {
                    System.out.print((j + 1) + " ");
                }
                for (int c = 0; c < dim; c++) {
                    if (have(players, board_coor[j][c])) {
                        System.out.print('H');
                    } else if (board_coor[j][c].equals(players[len - 1])) {
                        System.out.print('F');
                    } else {
                        System.out.print(".");
                    }
                }
                if (j < 9) {
                    System.out.print(" 0" + (j + 1));
                } else {
                    System.out.print(" " + (j + 1));
                }
                System.out.println();
            }
        }

        System.out.print(" ");
        for (int i = 0; i < dim; i++) {
            System.out.print(Character.toString('A' + i));
        }
        System.out.println();
    }

    public static String[] positionQuery(int dimension, Scanner stdin) {
        String ori = stdin.next();
        String des = stdin.next();
        String[] pos = new String[2];
        pos[0] = ori;
        pos[1] = des;
        System.out.println("Provide origin and destination coordinates");
        System.out.println("Enter two positions between A1-"
                + Character.toString('A' + (dimension - 1)) + dimension);
        return pos;
    }
    /**
     * Print the main menu and query the user for an entry selection.
     *
     * @param figureToMove the figure type that has the next move
     * @param stdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */
    public static int mainMenuQuery(char figureToMove, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

        int input = -1;
        while (input == -1) {
            System.out.println(nextFigure + " to move");
            System.out.println(MAIN_MENU);

            boolean validInput = false;
            if (stdin.hasNextInt()) {
                input = stdin.nextInt();
                validInput = input > 0 && input <= MENU_ENTRIES;
            }

            if (!validInput) {
                System.out.println("Please enter valid number.");
                input = -1; // reset input variable
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    public static Path fileQuery(Scanner test_in) {
        return (Paths.get(""));
    }
}
