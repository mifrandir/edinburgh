
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all user interface related
 * functionality such as printing menus and displaying the game board.
 */
public class FoxHoundUI {
    public static void main(String[] args) {}

    public static String[] positionQuery(int dimension, Scanner p) {
        Objects.requireNonNull(p, "Given Scanner must not be null");
        String[] result = new String[2];
        String original = "";
        String destination = "";
        int i = 0;
        boolean re = true;

        while (re) {
            PrintWriter fs = new PrintWriter(System.out);
            fs.println("Provide origin and destination coordinates.\n"
                    + "Enter two positions between A1-H8:\n");
            fs.flush();
            // fs.close();
            original = p.next();
            original = original.trim();

            destination = p.next();
            destination = destination.trim();

            if (original.length() != 2 || destination.length() != 2) {
                System.err.println("ERROR: Please enter valid coordinate pair separated by space.");
            } else if (isValidBoard(dimension, original) && isValidBoard(dimension, destination)) {
                result[0] = original;
                result[1] = destination;
                re = false;
            }
        }

        return result;
    }

    public static Path fileQuery(Scanner p) {
        String[] game;
        Path z;
        String[] game1;
        char nextmove;
        PrintWriter sc = new PrintWriter(System.out);
        sc.println("Enter the path: ");
        sc.flush();
        sc.close();

        String a = p.nextLine();
        String[] b = a.split(" ");
        Path d = Paths.get(b[b.length - 1]);
        try {
            game = onlyload(d);

            if (FoxHoundIO.secondCondition(game)) {
                nextmove = game[0].charAt(0);
                game1 = Arrays.copyOfRange(game, 1, game.length);
                if (FoxHoundIO.loadGame(game1, d) == '#') {
                    System.err.println("ERROR: Loading from file failed.");
                } else if (!FoxHoundIO.saveGame(game1, nextmove, d)) {
                    System.err.println("ERROR: Saving from file failed.");
                } else {
                    return d;
                }
            } else {
                z = Paths.get(b[b.length - 1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Paths.get(a);
    }

    public static String[] onlyload(Path p) throws IOException {
        String a = "";
        String[] ne = {};
        char nextmove = '#';
        File b = new File(p.toString());
        String[] game1 = {};
        String path = b.getAbsolutePath();
        Path bd = Paths.get(path);
        if (!b.exists()) {
            b.createNewFile();
        }

        try {
            game1 = a.split(" ");

            //            if(FoxHoundIO.secondCondition(game1) ){
            //                nextmove = game1[0].charAt(0);
            //                ne = Arrays.copyOfRange(game1,1,game1.length);
            //            }

        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println("Error");
        }

        return game1;
    }

    /** Number of main menu entries. */
    private static final int MENU_ENTRIES = 2;
    /** Main menu display string. */
    private static final String MAIN_MENU = "\n1. Move\n2. Exit\n\nEnter 1 - 2:";

    /** Menu entry to select a move action. */
    public static final int MENU_MOVE = 1;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 2;

    public static void displayBoard(String[] players, int dimension)
            throws IllegalArgumentException, NullPointerException {
        // TODO implement me
        String[][] f = FoxHoundUtils.getTran(players, dimension);
        System.out.println("  ");
        char value = 'A';
        for (int i = 0; i < f.length; i++) {
            System.out.print((char) (value + i));
        }

        System.out.println("  \n");

        for (int a = 0; a < f.length; a++) {
            System.out.print((a + 1) + " ");
            for (int b = 0; b < f.length; b++) {
                if (f[a][b] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(f[a][b]);
                }
            }
            System.out.println(" " + (a + 1));
        }
        System.out.println("");
        System.out.print("  ");
        for (int i = 0; i < f.length; i++) {
            System.out.print((char) (value + i));
        }
    }

    /**
     * Print the main menu and query the user for an entry selection.
     *
     * @paramfigureToMove the figure type that has the next move
     * @paramstdin a Scanner object to read user input from
     * @return a number representing the menu entry selected by the user
     * @throws IllegalArgumentException if the given figure type is invalid
     * @throws NullPointerException if the given Scanner is null
     */

    public static boolean isValidBoard(int dimension, String str) {
        boolean result = false;
        char[] c = FoxHoundUtils.exchange(dimension);
        int orrow;
        int rocol;

        try {
            orrow = FoxHoundUtils.getrowPos(str);
            rocol = FoxHoundUtils.getcolPos(str, dimension);

            if (0 <= orrow && orrow <= dimension - 1 && rocol >= 0 && rocol <= dimension - 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

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
}
