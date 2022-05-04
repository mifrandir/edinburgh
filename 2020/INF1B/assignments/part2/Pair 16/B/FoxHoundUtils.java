/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */

import java.util.Arrays;

public class FoxHoundUtils {
    // ATTENTION: Changing the following given constants can
    // negatively affect the outcome of the auto grading!

    /**
     * Default dimension of the game board in case none is specified.
     */
    public static final int DEFAULT_DIM = 8;
    /**
     * Minimum possible dimension of the game board.
     */
    public static final int MIN_DIM = 4;
    /**
     * Maximum possible dimension of the game board.
     */
    public static final int MAX_DIM = 26;

    /**
     * Symbol to represent a hound figure.
     */
    public static final char HOUND_FIELD = 'H';
    /**
     * Symbol to represent the fox figure.
     */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...
    public static void main(String[] args) {
        String[] d = initialisePositions(8);
        System.out.println(Arrays.toString(d));
        // String
    }

    public static boolean isValidMove(
            int dimension, String[] players, char f, String origin, String destination) {
        boolean result = false;
        String[][] game = getTran(players, dimension);
        int[] rowPos = getrowPosition(players);
        int[] colPos = getcolPosition(players, dimension);

        int foxrow = rowPos[rowPos.length - 1];
        int foxcol = colPos[colPos.length - 1];
        int originrow = getrowPos(origin);
        int origincol = getcolPos(origin, dimension);
        int rowDestination = getrowPos(destination);
        int colDestination = getcolPos(destination, dimension);

        boolean checkBorder = checkBorders(rowDestination, colDestination, dimension);
        boolean isInitialized = isInitialized(game, rowDestination, colDestination);

        if (isInitialized) {
            System.err.println(
                    "Move is expected to be invaid if the given destination is already occupied.");
        } else if (f == 'F' && (originrow != foxrow || origincol != foxcol)) {
            System.err.println(
                    "Move is expected to be invalid if the figure in the origin does not match the given figure type.");
        } else if ((f == 'F') && checkBorder) {
            if (checkColConditionFox(origincol, dimension)
                    && checkRowConditionFox(originrow, dimension)) {
                result = (rowDestination == originrow - 1) && (colDestination == origincol - 1);
            } else if (checkRowConditionFox(originrow, dimension) && origincol == 0) {
                result = (rowDestination == originrow - 1) && (colDestination == origincol + 1);
            } else if (checkRowConditionFox(originrow, dimension)) {
                result = (rowDestination == originrow - 1 && colDestination == origincol - 1)
                        || (rowDestination == originrow - 1 && colDestination == origincol + 1);
            } else if (checkColConditionFox(origincol, dimension)) {
                result = (rowDestination == originrow - 1 && colDestination == origincol - 1)
                        || (rowDestination == originrow + 1 && colDestination == origincol - 1);
            } else if (origincol == 0) {
                result = (rowDestination == originrow - 1 && colDestination == origincol + 1)
                        || (rowDestination == originrow + 1 && colDestination == origincol + 1);
            } else {
                result = (rowDestination == originrow - 1 && colDestination == origincol - 1)
                        || (rowDestination == originrow + 1 && colDestination == origincol + 1)
                        || (rowDestination == originrow - 1 && colDestination == origincol + 1)
                        || (rowDestination == originrow + 1 && colDestination == origincol - 1);
            }

        } else if ((f == 'H') && checkBorder) {
            if (checkColConditionFox(origincol, dimension)
                    && checkRowConditionFox(originrow, dimension)) {
                result = false;
            } else if (checkRowConditionFox(originrow, dimension) && origincol == 0) {
                result = false;
            } else if (checkColConditionFox(origincol, dimension)) {
                result = (rowDestination == originrow - 1) && (colDestination == origincol - 1);
            } else if (checkRowConditionFox(originrow, dimension)) {
                result = false;
            } else if (origincol == 0) {
                result = (rowDestination == originrow - 1) && (colDestination == origincol + 1);
            } else {
                result = ((rowDestination == originrow + 1) && (colDestination == origincol - 1))
                        || ((rowDestination == originrow + 1) && (colDestination == origincol + 1));
            }
        }
        return result;
    }

    public static String[] initialisePositions(int dimension) {
        if (dimension == 0 || dimension < 0) {
            throw new IllegalArgumentException("Length can't be zero");
        }

        int length = dimension / 2 + 1;
        int fox = dimension / 2;
        // TODO implement me

        String[] f = new String[length];
        char[] d = exchange(dimension);

        int b = 0;
        for (int a = 1; a < d.length; a += 2) {
            f[b] = d[a] + Integer.toString(1);
            b++;
        }
        char fox1 = d[fox];
        f[f.length - 1] = fox1 + Integer.toString(dimension);

        return f;
    }

    public static boolean isInitialized(String[][] f, int rowPos, int colPos) {
        boolean result = true;
        if (f[rowPos][colPos] == null) {
            result = false;
        }
        return result;
    }

    public static boolean isFoxWin(String pos) {
        char row = pos.charAt(0);
        boolean result = false;

        if (row == 'B') {
            result = true;
        }

        return result;
    }

    public static boolean isHoundWin(String[] players, int dimension) {
        if (dimension == 0 || dimension < 0) {
            throw new IllegalArgumentException("Length can't be zero");
        } else if (players == null) {
            throw new NullPointerException("Length can't be zero");
        }

        int fox = dimension / 2;
        boolean result = false;

        String[][] game = getTran(players, dimension);
        int[] rowPos = getrowPosition(players);
        int[] colPos = getcolPosition(players, dimension);

        int foxrow = rowPos[fox];
        int foxcol = colPos[fox];

        if (checkColConditionFox(foxcol, dimension) || checkRowConditionFox(foxrow, dimension)) {
            if (checkColConditionFox(foxcol, dimension)
                    && checkRowConditionFox(foxrow, dimension)) {
                result = game[foxrow - 1][foxcol - 1].equals("H");
            } else if (checkRowConditionFox(foxrow, dimension) && foxcol == 0) {
                result = game[foxrow - 1][foxcol + 1].equals("H");
            } else if (checkRowConditionFox(foxrow, dimension)) {
                result = checkRowCondition(foxrow, foxcol, game);
            } else if (checkColConditionFox(foxcol, dimension)) {
                result = checkColCondition(foxrow, foxcol, game);
            }
        } else if (foxcol == 0) {
            result = (game[foxrow - 1][foxcol + 1].equals("H"))
                    && (game[foxrow + 1][foxcol + 1].equals("H"));
        } else {
            if (game[foxrow - 1][foxcol - 1] != null && game[foxrow + 1][foxcol + 1] != null
                    && game[foxrow - 1][foxcol + 1] != null
                    && game[foxrow + 1][foxcol - 1] != null) {
                result = game[foxrow - 1][foxcol - 1].equals("H")
                        && game[foxrow + 1][foxcol + 1].equals("H")
                        && game[foxrow - 1][foxcol + 1].equals("H")
                        && game[foxrow + 1][foxcol - 1].equals("H");
            } else {
                result = false;
            }
        }

        return result;
    }

    public static boolean checkRowCondition(int row, int col, String[][] f) {
        boolean result = false;
        if (row == 0 || col == 0) {
            throw new NullPointerException("No zero");
        }
        if (f[row - 1][col - 1] != null && f[row - 1][col + 1] != null) {
            if (f[row - 1][col - 1].equals("H") && f[row - 1][col + 1].equals("H")) {
                result = true;
            }
        }

        return result;
    }

    public static boolean checkColCondition(int row, int col, String[][] f) {
        boolean result = false;
        if (row == 0 || col == 0) {
            throw new NullPointerException("No zero");
        }
        if (f[row + 1][col - 1] != null && f[row - 1][col - 1] != null) {
            if (f[row + 1][col - 1].equals("H") && f[row - 1][col - 1].equals("H")) {
                result = true;
            }
        }

        return result;
    }

    public static boolean checkRowConditionFox(int row, int dimension) {
        boolean result = false;

        if (row == dimension - 1) {
            result = true;
        }
        return result;
    }

    public static boolean checkColConditionFox(int col, int dimension) {
        boolean result = false;

        if (col == dimension - 1) {
            result = true;
        }
        return result;
    }

    public static boolean checkBorders(int row, int col, int dimension) {
        boolean result = false;
        if (row >= 0 || row <= dimension - 1 || col >= 0 || col <= dimension - 1) {
            result = true;
        }
        return result;
    }

    public static char[] exchange(int dimension) {
        char[] d = new char[dimension];
        if (dimension == 0 || dimension < 0) {
            throw new IllegalArgumentException("Length can't be zero");
        }

        for (int i = 0; i < d.length; i++) {
            d[i] = (char) ('A' + i);
        }
        return d;
    }

    public static String[][] getTran(String[] players, int dimension) {
        if (dimension == 0 || dimension < 0 || players == null) {
            throw new IllegalArgumentException("Length can't be zero");
        }

        int fox = dimension / 2;
        int[] rowPos = getrowPosition(players);
        int[] colPos = getcolPosition(players, dimension);
        String[][] f = new String[dimension][dimension];

        for (int d = 0; d < fox; d++) {
            f[rowPos[d]][colPos[d]] = "H";
        }

        f[rowPos[fox]][colPos[fox]] = "F";

        return f;
    }

    public static int[] getcolPosition(String[] players, int dimension) {
        char[] c = exchange(dimension);
        int[] my = new int[players.length];

        for (int d = 0; d < players.length; d++) {
            for (int b = 0; b < dimension; b++) {
                if (players[d].charAt(0) == c[b]) {
                    my[d] = b;
                }
            }
        }

        return my;
    }

    public static int getcolPos(String players, int dimension) {
        int result = 0;
        char[] c = exchange(dimension);
        for (int b = 0; b < dimension; b++) {
            if (players.charAt(0) == c[b]) {
                result = b;
            }
        }

        return result;
    }

    public static int getrowPos(String player) {
        String temp;
        temp = String.valueOf(player.charAt(1));
        int rowpos = Integer.parseInt(temp) - 1;

        return rowpos;
    }

    public static int[] getrowPosition(String[] player) {
        int[] me = new int[player.length];
        String temp;
        int a = player[1].charAt(1);
        for (int i = 0; i < player.length; i++) {
            temp = String.valueOf(player[i].charAt(1));
            me[i] = Integer.parseInt(temp) - 1;
        }
        return me;
    }
}