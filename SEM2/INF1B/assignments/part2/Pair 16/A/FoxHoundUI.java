import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Objects;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 4;
    /** Main menu display string. */
    private static final String MAIN_MENU =
        "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit\n\nEnter 1 - 4:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;
    /** Menu entry to save file. */
    public static final int MENU_SAVE = 2;
    /** Menu entry to load file. */
    public static final int MENU_LOAD = 3;

    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Prints the game board to the console.
     *
     * @param players String array which represent all game pieces positions.
     * @param dimension An int that specifies dimension of game board.
     * @throws IllegalArgumentException if a incorrect dimension inputted.
     * @throws NullPointerException if a incorrect dimension.
     */
    public static void displayBoard(String[] players, int dimension) {
        if (players == null) {
            throw new NullPointerException("Null array not allowed.");
        }

        if (dimension < 4 || dimension > 26) {
            throw new IllegalArgumentException("Input a dimension that is between 4 and 26.");
        }

        if (dimension < 10) {
            displaySmallBoard(players, dimension);
        } else {
            displayBigBoard(players, dimension);
        }
    }

    /**
     * Prints the game board to the console when dimension is less than 10.
     *
     * @param players String array which represent all game pieces positions.
     * @param dimension An int that specifies dimension of game board.
     */
    public static void displaySmallBoard(String[] players, int dimension) {
        System.out.println("  " + alphabet.substring(0, dimension) + "  \n");
        for (int i = 1; i < dimension + 1; i++) {
            System.out.printf("%d ", i);

            for (int j = 0; j < dimension; j++) {
                if (isOccupied(players, i, j)) {
                    System.out.print(foxOrHound(players, i, j));
                } else {
                    System.out.print(".");
                }
            }
            System.out.printf(" %d\n", i);
        }
        System.out.println("\n  " + alphabet.substring(0, dimension) + "  ");
    }

    /**
     * Prints the game board to the console when dimension is greater than 9
     *
     * @param players String array which represent all game pieces positions.
     * @param dimension An int that specifies dimension of game board.
     */
    public static void displayBigBoard(String[] players, int dimension) {
        System.out.println("   " + alphabet.substring(0, dimension) + "   \n");
        for (int i = 1; i < dimension + 1; i++) {
            if (i < 10) {
                System.out.printf("0%d ", i);
            } else {
                System.out.printf("%d ", i);
            }

            for (int j = 0; j < dimension; j++) {
                if (isOccupied(players, i, j)) {
                    System.out.print(foxOrHound(players, i, j));
                } else {
                    System.out.print(".");
                }
            }

            if (i < 10) {
                System.out.printf(" 0%d\n", i);
            } else {
                System.out.printf(" %d\n", i);
            }
        }
        System.out.println("\n   " + alphabet.substring(0, dimension) + "   ");
    }

    /**
     * helper function that checks if a position is occupied with a piece.
     *
     * @param positions String array which represent all game pieces positions.
     * @param row An int that specifies row number of game board.
     * @param col An int that specifies column number of game board.
     * @return boolean which determines if a position is occupied.
     */
    public static Boolean isOccupied(String[] positions, int row, int col) {
        for (String position : positions) {
            if (position.charAt(0) == alphabet.charAt(col) && Integer.parseInt(position.substring(1)) == row) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper function that checks if a position is occupied with either a hound or fox.
     *
     * @param positions String array which represent all game pieces positions.
     * @param row An int that specifies row number of game board.
     * @param col An int that specifies column number of game board.
     * @return char which determines if a position is with either a hound or fox.
     */
    public static char foxOrHound(String[] positions, int row, int col) {
        char player = 'X';
        for (int i = 0; i < positions.length - 1; i++) {
            if (positions[i].charAt(0) == alphabet.charAt(col) && Integer.parseInt(positions[i].substring(1)) == row) {
                player = 'H';
            }
        }
        if (positions[positions.length - 1].charAt(0) == alphabet.charAt(col) && Integer.parseInt(positions[positions.length - 1].substring(1)) == row) {
            player = 'F';
        }
        return player;
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
        if (figureToMove != FoxHoundUtils.FOX_FIELD
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure =
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

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
     * Asks user for file path when saving/loading games.
     *
     * @param test_in a Scanner object to read user input from
     * @return path object representing the menu entry selected by the user
     * @throws NullPointerException if the given Scanner is null
     */
    public static Path fileQuery(Scanner test_in) {
        Objects.requireNonNull(test_in, "Given Scanner must not be null");

        System.out.println("Enter file path:");
        String text = test_in.nextLine();
        if (text == null) {
            throw new NullPointerException("Cannot input empty string.");
        }
        return Paths.get(text);
    }

    /**
     * Print the main menu and query the user for an entry selection.
     *
     * @param dim An int that specifies dimension of game board.
     * @param test_in a Scanner object to read user input from.
     * @throws NullPointerException if the given Scanner is null.
     * @throws IllegalArgumentException if illegal dimension given.
     * @return a string array which is the origin and destination for a piece.
     */
    public static String[] positionQuery(int dim, Scanner test_in) {
        Objects.requireNonNull(test_in, "Given Scanner must not be null");

        if (dim < 4 || dim > 26) {
            throw new IllegalArgumentException("Input a dimension that is between 4 and 26.");
        }

        while (true) {
            System.out.println("Provide origin and destination coordinates.");
            System.out.printf("Enter two positions between A1-%c%d:\n", alphabet.charAt(dim - 1), dim);
            String input = test_in.nextLine();

            if (input == null) {
                throw new NullPointerException(("Input a string for both origin and destination."));
            }
            try {
                String[] parts = input.split(" ", 2);
                boolean onBoard = FoxHoundUtils.onBoard(dim, parts[0]) && FoxHoundUtils.onBoard(dim, parts[1]);

                if (onBoard) {
                    return parts;
                } else {
                    System.out.println();
                    System.err.println("ERROR: Please enter valid coordinate pair separated by space.\n");
                }
            } catch (Exception e) {
                // If user does not supply at least two coordinates.
                System.out.println("ERROR: Please enter valid coordinate pair separated by space.\n");
            }
        }
    }
}







