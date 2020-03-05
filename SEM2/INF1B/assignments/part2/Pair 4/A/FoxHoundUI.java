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
    private static final String MAIN_MENU =
            "\n1. Move \n2. Save Game\n 3. Load Game\n 4. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;
    /** Menu entry to load game. */
    public static final int MENU_LOAD = 3;
    /** Menu entry to save game. */
    public static final int MENU_SAVE = 2;

    /**
     * Generates a string containing the border of the board to be displayed along the top. E.g.
     * "ABCDEF"
     * @param dimension Board dimension.
     * @return A string containing the border.
     */
    private static String makeXKey(int dimension) {
        FoxHoundUtils.validateDimension(dimension);
        String padding =
                dimension >= 10 ? "   " : "  "; // Size of padding changes depending on dimension.
        String letters_key = padding; // Start output with padding.
        for (int i = 1; i <= dimension; i++) { // Add characters from alphabet. One character per dimension size.
            letters_key = letters_key + FoxHoundUtils.posToChar(i);
        }
        letters_key = letters_key + padding; // Add padding on the right hand size.
        return letters_key;
    }

    /**
     * @param dimension Board size.
     * @return A number of dots to represent a row of the board not filled with any players.
     */
    private static String genEmptyLine(int dimension) {
        FoxHoundUtils.validateDimension(dimension);
        String line = "";
        for (int i = 0; i < dimension; i++) { // Add a number of dots equal to the dimension size.
            line += ".";
        }
        return line;
    }

    /**
     * Generates a value in the Y key - the key to be displayed on the left and right of the board.
     * @param line_num The line to make the key for.
     * @param dimension Board size.
     * @return A string containing the part of the Y key.
     */
    private static String makeDisplayNum(int line_num, int dimension) {
        FoxHoundUtils.validateDimension(dimension);
        String num_str = String.valueOf(line_num); // Convert int to string.
        if (num_str.length() == 1 && dimension >= 10) { // If a 0 needs to be added on left.
            num_str = "0" + num_str;
        }
        return num_str;
    }

    /**
     * Displays board to standard out.
     * @param players Players to display on board.
     * @param dimension Board dimension
     */
    static void displayBoard(String[] players, int dimension) {
        FoxHoundUtils.validateDimension(dimension);

        String[] board_lines = new String[dimension];
        for (int i = 0; i < dimension; i++) {
            board_lines[i] = genEmptyLine(dimension); // Set board to a bunch of empty dots.
        }
        for (int i = 0; i < players.length; i++) {
            char icon;
            if (i == players.length - 1) { // If i is the index of the fox.
                icon = FoxHoundUtils.FOX_FIELD;
            } else {
                icon = FoxHoundUtils.HOUND_FIELD;
            }
            String player = players[i];
            int[] position = FoxHoundUtils.strToPos(player);
            if (position == null) {
                throw new IllegalArgumentException(
                        "Can't render as one of player coords was invalid.");
            }
            char[] old_row = board_lines[position[1] - 1].toCharArray(); // Get row that pawn is on.
            old_row[position[0] - 1] = icon; // Set player piece char to F or H.
            String new_row = String.valueOf(old_row); // Convert back to string.
            board_lines[position[1] - 1] = new_row; // Replace old row with new modified row.
        }
        String key = makeXKey(dimension);
        System.out.print(key + "\n\n");
        for (int i = 0; i < board_lines.length;
                i++) { // Display all board lines with padding and keys.
            String y_num = makeDisplayNum(i + 1, dimension);
            System.out.print(y_num);
            System.out.print(" ");
            System.out.print(board_lines[i]);
            System.out.print(" ");
            System.out.print(y_num + "\n");
        }
        System.out.print("\n" + key);
    }

    /**
     * Ask user for input and validate it.
     * @param dimension Board size.
     * @param stdin Where to read input from.
     * @return Their inputs. [0] is source [1] is destination.
     */
    static String[] positionQuery(int dimension, Scanner stdin) {
        FoxHoundUtils.validateDimension(dimension);
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        while (true) { // Keep asking util valid input is given.
            System.out.print(
                    "Provide origin and destination coordinates.\nEnter two positions between A1-H8:\n");
            String input = stdin.nextLine(); // Read input.
            String[] args = input.split(" ");
            if (args.length != 2) {
                System.out.print("Invalid number of coords. Need 2\n");
                continue;
            }
            int[] coords_1 = FoxHoundUtils.strToPos(
                    args[0]); // Check coords are valid. These will be null if not.
            int[] coords_2 = FoxHoundUtils.strToPos(args[1]);
            if (coords_1 == null || coords_2 == null) {
                System.err.print("ERROR: Please enter valid coordinate pair separated by space.\n");
                System.out.print("\n");
                continue;
            }
            if (!FoxHoundUtils.tileIsOnBoard(dimension, coords_1)
                    || !FoxHoundUtils.tileIsOnBoard(dimension, coords_2)) {
                System.err.print("ERROR: Input coords are not on board..\n");
                continue;
            }
            String[] to_return = {FoxHoundUtils.posToStr(coords_1[0], coords_1[1]),
                    FoxHoundUtils.posToStr(coords_2[0], coords_2[1])};
            return to_return;
        }
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

    /**
     * Asks the user for a filepath.
     * @param stdin User input scanner.
     * @return The path the user entered.
     */
    static Path fileQuery(Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        System.out.println("Enter file path:");
        String input = stdin.nextLine(); // Read input.
        return Paths.get(input); // Convert to path.
    }
}
