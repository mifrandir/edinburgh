
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

    // displayBoard prints the current board position as ASCII art.
    public static void displayBoard(final String[] players, final int dim) {
        // TODO test me
        final int len = FoxHoundUtils.lenOf(dim);
        // I am using a StringBuilder instead of printing it straight to the output
        // in case I need to refactor later on and I am required to extract the entire
        // String before it's printed.
        final StringBuilder sb = new StringBuilder();
        buildColIndices(sb, dim);
        newLine(sb);
        final char[] pb = prebuildBoard(players, dim);
        for (int row = 0; row < dim; row++) {
            sb.append(FoxHoundUtils.padLeftZeros(row + 1, len));
            sb.append(' ');
            for (int col = 0; col < dim; col++) {
                sb.append(pb[col * dim + row]);
            }
            sb.append(' ');
            sb.append(FoxHoundUtils.padLeftZeros(row + 1, len));
            sb.append('\n');
        }
        newLine(sb);
        buildColIndices(sb, dim);
        System.out.println(sb.toString());
    }

    // prebuildBoard creates a char array containing only the inside of the board and
    // fills every cell with the correct character. This reduces run time complexity
    // by avoiding to check for every cell if it contains a character.
    private static char[] prebuildBoard(final String[] players, final int dim) {
        final char[] board = new char[dim * dim];
        for (int i = 0; i < board.length; i++) {
            board[i] = '.';
        }
        for (int i = 0; i < players.length - 1; i++) {
            board[FoxHoundUtils.toBoardIndex(players[i], dim)] = 'H';
        }
        board[FoxHoundUtils.toBoardIndex(players[players.length - 1], dim)] = 'F';
        return board;
    }

    // buildRowIndices adds a line containing the indices of the columns
    // to the given StringBuilder.
    private static void buildColIndices(final StringBuilder sb, final int dim) {
        String padding;
        if (dim < 10) {
            padding = "  ";
        } else {
            padding = "   ";
        }
        sb.append(padding);
        for (int i = 0; i < dim; i++) {
            sb.append('A' + i);
        }
        sb.append(padding);
        newLine(sb);
    }

    private static void newLine(final StringBuilder sb) {
        sb.append('\n');
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
    public static int mainMenuQuery(final char figureToMove, final Scanner stdin) {
        Objects.requireNonNull(stdin, "Given Scanner must not be null");
        if (figureToMove != FoxHoundUtils.FOX_FIELD && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        final String nextFigure = figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

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
                input = -1; // reset input variable... why?
            }

            stdin.nextLine(); // throw away the rest of the line
        }

        return input;
    }

    public static String[] nextMoveQuery(final char figureToMove, final Scanner stdin) {
        // TODO implement me
        return null;
    }
}
