import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
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
    private static final int MENU_ENTRIES = 4;
    /** Main menu display string. */
    private static final String MAIN_MENU =
            "\n1. Move\n2. Save Game\n3. Load Game\n4. Exit\n\nEnter 1 - 4:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;

    public static final int MENU_SAVE = 2;
    public static final int MENU_LOAD = 3;

    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;

    /**
     * Displays the board
     * @param players The array of all players as board Strings.
     * @param dimension The board dimension
     */
    public static void displayBoard(String[] players, int dimension) {
        Boolean doubleDigits = (dimension > 9);
        String spacing = "  ";
        if (doubleDigits) {
            spacing = "   ";
        } // check this

        String line1 = "";
        for (int i = 1; i <= dimension; i++) {
            line1 += (char) (64 + i);
        }

        line1 = spacing + line1 + spacing; // dubious

        System.out.print(line1 + "\n"
                + "\n");

        for (int i = 1; i <= dimension; i++) // iterate over row
        {
            String rownum = i + "";
            if (doubleDigits) {
                rownum = String.format("%02d", i); // pads with zeroes.
            }
            System.out.print(rownum + " ");
            for (int c = 1; c <= dimension; c++) // iterate over column
            {
                String toAdd = ".";
                String toBoardPos = ((char) (64 + c)) + "" + i; // 1 less than A plus C
                for (int p = 0; p < players.length; p++) {
                    if (toBoardPos.equals(players[p])) // if fox or hound here
                    {
                        if (p == players.length - 1) {
                            toAdd = "F";
                        } else {
                            toAdd = "H";
                        }
                    }
                }
                System.out.print(toAdd);
            }
            System.out.print(" " + rownum + "\n");
        }
        System.out.print("\n" + line1 + "\n");
    }

    /**
     * Fancier displayed version of displayBoard, almost identical in structure to displayBoard
     * @param players The array of all players as board Strings.
     * @param dimension The board dimension
     */
    public static void displayBoardFancy(String[] players, int dimension) {
        Boolean doubleDigits = (dimension > 9);
        String separator = "  ";
        for (int i = 0; i < dimension; i++) {
            separator += "|===";
        }
        separator += "|";

        // Column bars
        String columnbar = " ";
        for (int i = 0; i < dimension; i++) {
            columnbar = columnbar + "   " + (char) ((int) 'A' + i);
        }

        for (int i = 0; i < dimension * 2 + 3; i++) {
            if (i == 0 || i == dimension * 2 + 2) {
                System.out.println(columnbar);
            } else {
                if (i % 2 == 1) {
                    System.out.println(separator);
                } else {
                    // print row number
                    String rowNum = Integer.toString(i / 2) + " ";
                    if (doubleDigits) {
                        rowNum = String.format("%02d", (int) (i / 2));
                    }
                    System.out.print(rowNum);
                    // Begin loop checking if cell occupied etc
                    for (int c = 0; c < dimension; c++) {
                        int r = (int) (i / 2);
                        // c is column, r is row
                        System.out.print("| ");
                        String toAdd = " ";
                        String toBoardPos = ((char) (65 + c)) + "" + r; // A plus C plus row
                        for (int p = 0; p < players.length; p++) {
                            if (players[p].equals(toBoardPos)) {
                                if (p == players.length - 1) {
                                    toAdd = "F";
                                } else {
                                    toAdd = "H";
                                }
                            }
                        }
                        System.out.print(toAdd);
                        System.out.print(" ");
                    }
                    System.out.print("| " + rowNum + "\n");
                }
            }
        }
    }

    /**
     * Lists the player positions for move-making fidelity
     * @param players The array of all players as board Strings
     */
    public static void listPositions(String[] players) {
        System.out.print("Hound positions: ");
        for (int i = 0; i < players.length - 1; i++) {
            System.out.print("[" + players[i] + "] ");
        }
        System.out.println();
        System.out.println("Fox position: [" + players[players.length - 1] + "]");
    }

    /**
     * Loops until the user enters two valid board positions, see {@link FoxHoundUtils#isValidMove}
     * @param dim The board dimensions
     * @param s STDIN Scanner
     * @return The valid board positions
     */
    public static String[] positionQuery(int dim, Scanner s) {
        Boolean validityflag = false;
        String[] inputsplit = null;

        while (!validityflag) {
            System.out.println("Provide origin and destination coordinates.\n"
                    + "Enter two positions between A1-H8:\n"); // Because the codegrade marker is
                                                               // particularly anal about this being
                                                               // the query string
            String input = s.nextLine();

            String regex = "(\\w)(\\d+) (\\w)(\\d+)";
            if (input.matches(regex)) {
                inputsplit = input.split(" ");
                if ((FoxHoundUtils.withinBounds(inputsplit[0], dim))
                        && (FoxHoundUtils.withinBounds(
                                inputsplit[1], dim))) // If valid with respect to board dims
                {
                    validityflag = true;
                }
            } else {
                System.err.println(
                        "ERROR: Please enter valid coordinate pair separated by space.\n"); //[sic]
            }
        }
        return inputsplit;
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
     * Asks the user for a filename and returns the Path
     * @param STDIN standard input
     * @return The Path object for the entered filepath
     */
    public static Path fileQuery(Scanner STDIN) {
        System.out.println("Enter file path:");
        String givenPath = STDIN.nextLine();
        Path fp = Paths.get(givenPath);
        return fp;
    }
}