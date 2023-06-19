import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;


/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {
    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU = "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;

    /**
     * Displays the board with the figures in the apporpriate fields.
     *
     * @param players Contains the coordinates of all the figures
     * @param dimension The dimension of the board
     */

    public static void displayBoard(String[] players, int dimension) {
        String dot = ".";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String dimAlphabet = "";
        for (int i = 0; i < dimension; i++) {
            dimAlphabet = dimAlphabet + alphabet.charAt(i);
        }
        String[] rows = new String[dimension];
        for (int i = 0; i < dimension; i++) {
            rows[i] = Integer.toString(i + 1) + " " + dot.repeat(dimension) + " "
                    + Integer.toString(i + 1);
            for (int b = 0; b < dimension / 2; b++) {
                if ((players[b].charAt(1)) == (char) (i + 1 + '0')) {
                    for (int a = 0; a < dimension; a++) {
                        int A = a + 2;
                        if (players[b].charAt(0) == alphabet.charAt(a)) {
                            char[] rowChar = rows[i].toCharArray();
                            rowChar[a + 2] = 'H';
                            rows[i] = String.valueOf(rowChar);
                        }
                    }
                }
            }
            if (players[dimension / 2].charAt(1) == (char) (i + 1 + '0')) {
                for (int c = 0; c < dimension; c++) {
                    if (players[dimension / 2].charAt(0) == alphabet.charAt(c)) {
                        char[] rowChar = rows[i].toCharArray();
                        rowChar[c + 2] = 'F';
                        rows[i] = String.valueOf(rowChar);
                    }
                }
            }
        }
        System.out.println("  " + dimAlphabet);
        for (int i = 0; i < dimension; i++) {
            System.out.println(rows[i]);
        }
        System.out.println("  " + dimAlphabet);
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

    public static String[] positionQuery(int dimension, Scanner stdin) {
        boolean valid = false;
        Scanner stdIn = new Scanner(System.in);
        String[] value = new String[2];
        String alphabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean va1, va2, va3, va4;
        while (true) {
            System.out.println("Provide origin and destination coordinates.\n"
                    + "Enter two positions between A1-H8:");
            String all = stdIn.nextLine();
            if (all.length() < 5) {
                System.out.println("ERROR: Please enter valid coordinate pair separated by space.");
                continue;
            }
            va1 = Character.isDigit(all.charAt(1));
            va2 = Character.isDigit(all.charAt(4));
            va3 = (all.charAt(0)) >= 'A' && (all.charAt(0)) <= ((char) 'A' + dimension);
            va4 = (all.charAt(3)) >= 'A' && (all.charAt(3)) <= ((char) 'A' + dimension);
            value[0] = "" + all.charAt(0) + all.charAt(1);
            value[1] = "" + all.charAt(3) + all.charAt(4);
            valid = va1 && va2 && va3 && va4;
            if (!valid) {
                System.out.println("Invalid coordinates.");
                continue;
            } else {
                System.out.println(value[0] + " " + value[1]);
                break;
            }
        }
        return value;
    }

    public static Path fileQuery(Scanner stdin) {
        String textPath = "ahjsajs";
        Path path = Paths.get(textPath);
        return path;
    }
}
