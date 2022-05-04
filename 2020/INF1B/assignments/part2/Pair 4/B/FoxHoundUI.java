import static java.lang.Character.isDigit;

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
    /**
     * Number of main menu entries.
     */
    private static final int MENU_ENTRIES = 4;
    /**
     * Main menu display string.
     */
    private static final String MAIN_MENU =
            "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit \n\nEnter 1 - 4:";
    /**
     * Menu entry to select a move action.
     */
    public static final int MENU_MOVE = 1;
    /**
     * Menu entry to terminate the program.
     */
    public static final int MENU_EXIT = 4;
    /**
     * Menu entry to save the program.
     */
    public static final int MENU_SAVE = 2;
    /**
     * Menu entry to load the program.
     */
    public static final int MENU_LOAD = 3;

    /**
     *
     * This method generates an ascii representation of the board using a string builder for the
     * first 2 and last 2 lines and then a 2D array for the board.
     *
     * @param players current position of all figures on the board in board coordinates
     * @param dimension the dimension of the game board
     */
    public static void displayBoard(String[] players, int dimension) {
        StringBuilder board = null;
        if (dimension < 10) {
            board = new StringBuilder();
            board.append("  ");
            for (int i = 0; i < dimension; i++) {
                board.append(FoxHoundUtils.ALPHABET[i]);
            }
            board.append("  ");
            System.out.print(board.toString());
            System.out.println("\n");
            String[][] outputArray = new String[dimension][dimension + 4];
            for (int i = 1; i < dimension + 1; i++) {
                for (int j = 0; j < dimension + 4; j++) {
                    if (j == 0 || j == (dimension + 3)) {
                        outputArray[i - 1][j] = Integer.toString(i);
                    } else if (j == 1 || j == (dimension + 2)) {
                        outputArray[i - 1][j] = " ";
                    } else {
                        if (isHound((j - 2), i, dimension, players) == true) {
                            outputArray[i - 1][j] = "H";
                        } else if (isFox((j - 2), i, dimension, players) == true) {
                            outputArray[i - 1][j] = "F";
                        } else {
                            outputArray[i - 1][j] = ".";
                        }
                    }
                }
            }
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension + 4; j++) {
                    System.out.print(outputArray[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.print(board.toString());
            System.out.println();

        } else {
            board = new StringBuilder();
            board.append("   ");
            for (int i = 0; i < dimension; i++) {
                board.append(FoxHoundUtils.ALPHABET[i]);
            }
            board.append("   ");
            System.out.print(board.toString());
            System.out.println("\n");

            String[][] outputArray = new String[dimension][dimension + 4];
            for (int i = 1; i < dimension + 1; i++) {
                for (int j = 0; j < dimension + 4; j++) {
                    if (j == 0 || j == (dimension + 3)) {
                        if (i < 10) {
                            outputArray[i - 1][j] = "0" + (i);
                        } else {
                            outputArray[i - 1][j] = Integer.toString(i);
                        }
                    } else if (j == 1 || j == (dimension + 2)) {
                        outputArray[i - 1][j] = " ";
                    } else {
                        if (isHound((j - 2), i, dimension, players) == true) {
                            outputArray[i - 1][j] = "H";
                        } else if (isFox((j - 2), i, dimension, players) == true) {
                            outputArray[i - 1][j] = "F";
                        } else {
                            outputArray[i - 1][j] = ".";
                        }
                    }
                }
            }
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension + 4; j++) {
                    System.out.print(outputArray[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            System.out.print(board.toString());
            System.out.println();
        }
    }

    /**
     *
     * This method takes a coordinate in the form (x,y) and returns if a hound is in that position
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param dimension dimension of board
     * @param players array representing all players in board coordinates
     * @return returns a boolean that's true if a hound is in the position given
     */

    public static boolean isHound(int x, int y, int dimension, String[] players) {
        for (int i = 0; i < (dimension / 2); i++) {
            if ((FoxHoundUtils.convertToBoard(x, y).equals(players[i]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method takes a coordinate of form (x,y) and returns if a fox is in that position
     * @param x x coordinate
     * @param y y coordinate
     * @param dimension dimension of board
     * @param players array representing all players in board coordinates
     * @return returns a boolean that's true if a fox is in the position given
     */
    public static boolean isFox(int x, int y, int dimension, String[] players) {
        if (FoxHoundUtils.convertToBoard(x, y).equals(players[((dimension / 2))])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print the main menu and query the user for an entry selection.
     *
     * @param figureToMove the figure type that has the next move
     * @param stdin        a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException     if the given Scanner is null
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

    /**
     * This method takes two coordinates from the user, if input is invalid form repeatedly asks
     * user until valid coords are given
     *
     * @param dimension dimension of board
     * @param stdin scanner input from user
     * @return returns two valid coordinates
     */
    public static String[] positionQuery(int dimension, Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        boolean flag = false;

        String[] output = new String[2];
        char chars = FoxHoundUtils.ALPHABET[dimension - 1];

        while (!flag) {
            System.out.println("Provide origin and destination coordinates.");
            System.out.println("Enter two positions between A1-" + chars + dimension + ":");
            System.out.println();

            output[0] = stdin.next();
            output[1] = stdin.next();
            if (!stdin.hasNext()) {
                break;
            }

            if (FoxHoundUtils.isCoordValid(output[0], dimension)
                    && FoxHoundUtils.isCoordValid(output[1], dimension)) {
                flag = true;
            } else {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            }
        }
        return output;
    }

    /**
     * This method takes a file path from the user
     *
     * @param stdin scanner input from user
     * @return returns a file path
     * @throws NullPointerException if the input is null
     */
    public static Path fileQuery(Scanner stdin) {
        if (stdin == null) {
            throw new NullPointerException("Scanner is null");
        }
        System.out.print("Enter file path:");
        String output = stdin.next();

        Path Path = Paths.get(output);
        return Path;
    }
}