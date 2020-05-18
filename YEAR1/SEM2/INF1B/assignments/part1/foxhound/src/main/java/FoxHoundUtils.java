/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {
    // ATTENTION: Changing the following given constants can
    // negatively affect the outcome of the auto grading!

    /** Default dimension of the game board in case none is specified. */
    public static final int DEFAULT_DIM = 8;
    /** Minimum possible dimension of the game board. */
    public static final int MIN_DIM = 4;
    /** Maximum possible dimension of the game board. */
    public static final int MAX_DIM = 26;

    /** Symbol to represent a hound figure. */
    public static final char HOUND_FIELD = 'H';
    /** Symbol to represent the fox figure. */
    public static final char FOX_FIELD = 'F';

    // HINT Write your own constants here to improve code readability ...

    private static void checkDim(final int dim) throws IllegalArgumentException {
        if (dim > MAX_DIM) {
            throw new IllegalArgumentException(String.format(
                    "The given argument is greater than the maximum dimension. (MAX_DIM=%d, dim=%d)",
                    MAX_DIM, dim));
        }
        if (dim < MIN_DIM) {
            throw new IllegalArgumentException(String.format(
                    "The given argument is less than the minimum dimension. (MIN_DIM=%d, dim=%d)",
                    MIN_DIM, dim));
        }
    }

    public static String[] initialisePositions(final int dimension)
            throws IllegalArgumentException {
        checkDim(dimension);
        final String[] positions = new String[dimension / 2 + 1];
        for (int i = 1; i < dimension; i += 2) {
            // i = 2k + 1. Thus i/2=(2k+1)/2=k.
            positions[i / 2] = toBoardCoordinates(i, 0);
        }
        positions[dimension / 2] = toBoardCoordinates(dimension / 2, dimension - 1);
        return positions;
    }

    // toBoardCoordinates converts a given pair of (col, row) into the corresponding board
    // coordinate String. e.g. toBoardCoordinates(2,0) == "C1"
    public static String toBoardCoordinates(final int col, final int row) {
        return String.format("%c%d", toColLetter(col), row + 1);
    }

    // toBoardIndex converts a given set of board coordinates to the corresponding index in the
    // board array based on given dimensions.
    // An exception is thrown if the input length is off or the given coordinates don't fit the
    // dimensions.
    public static int toBoardIndex(final String coords, final int dim)
            throws IllegalArgumentException {
        final int len = lenOf(dim);
        if (coords.length() != len) {
            throw new IllegalArgumentException(String.format(
                    "The given coordinate string has a wrong length. (length=%d, string=\"%s\")",
                    coords.length(), coords));
        }
        final int col = toColIndex(coords.charAt(0));
        if (col >= dim) {
            throw new IllegalArgumentException(String.format(
                    "The given coordinate string does not contain a column within the given"
                            + "dimensions. (string=\"%s\", dim=%d)",
                    coords, dim));
        }
        if (col < 0) {
            throw new IllegalArgumentException(String.format(
                    "The given coordinate string does not contain a column greater than 0."
                            + "(string=\"%s\", dim=%d)",
                    coords, dim));
        }
        final int row = Integer.parseInt(coords.substring(1)) - 1;
        if (row >= dim) {
            throw new IllegalArgumentException(String.format(
                    "The given coordinate string does not contain a row within the given"
                            + "dimensions. (string=\"%s\", dim=%d)",
                    coords, dim));
        }
        if (row < 0) {
            throw new IllegalArgumentException(String.format(
                    "The given coordinate string does not contain a row greater than 0."
                            + "(string=\"%s\", dim=%d)",
                    coords, dim));
        }
        return col * dim + row;
    }

    public static int lenOf(final int dim) {
        // For sake of readability I do not use the ? operator. Personal preference.
        int len;
        if (dim < 10) {
            len = 2;
        } else {
            len = 3;
        }
        return len;
    }

    // toColLetter converts a given column index (zero-based) into the corresponding column letter.
    private static char toColLetter(final int col) {
        return (char) ('A' + col);
    }
    // toColIndex converts a given column letter into the corresponding column index (zero-based).
    private static int toColIndex(final char col) {
        return (int) (col - 'A');
    }

    // padLeftZeros takes any integer and adds zeroes to the left to reach a certain length.
    //
    // Implementation inspired by https://www.baeldung.com/java-pad-string.
    public static String padLeftZeros(final int val, final int len) {
        final String num = String.format("%d", val);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('0');
        }
        return sb.substring(num.length() + 1) + num;
    }

    public static String minCoord(final int __dim) {
        return "A1";
    }

    public static String maxCoord(final int dim) {
        return toBoardCoordinates(dim - 1, dim - 1);
    }

    // isValidMove checks whether a move is valid based on the given parameters. If the parameters
    // are invalid, an exception is thrown.
    //
    // A move is only invalid (e.g. this function returns false) if
    // a) The given figure is not at the origin, or
    // b) the given destination is not empty.
    public static boolean isValidMove(final int dim, final String[] players, final char fig,
            final String og, final String dest) throws IllegalArgumentException {
        // Check dimension limits.
        checkDim(dim);
        // Check whether given array is null.
        if (players == null) {
            throw new NullPointerException();
        }
        // Check whether the given figure is valid.
        if (fig != 'F' && fig != 'H') {
            throw new IllegalArgumentException(
                    String.format("Expected 'H' or 'F' as figure. (figure='%c')", fig));
        }
        // Converting all the coords to indices.
        // This checks for bound errors and allows for faster comparisons.
        int ogI = toBoardIndex(og, dim);
        int destI = toBoardIndex(dest, dim);
        int d = ogI % dim - destI % dim;
        if (d != 1 && d != -1) {
            return false;
        }
        d = ogI / dim - destI / dim;
        if (d != 1 && d != -1) {
            return false;
        } 
        int[] playersI = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            playersI[i] = toBoardIndex(players[i], dim);
        }
        // Check whether fig is at og.
        if (fig == 'F') {
            if (playersI[players.length - 1] != ogI) {
                return false;
            }
        } else {
            boolean found = false;
            for (int i = 0; i < players.length - 1; i++) {
                if (playersI[i] == ogI) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        // Check whether dest is empty.
        for (int i = 0; i < players.length; i++) {
            if (playersI[i] == destI) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFoxWin(String fox) {
        if (fox == null) {
            throw new NullPointerException();
        }
        if (fox.length() < 2) {
            throw new IllegalArgumentException("Invalid coordinate: " + fox);
        }
        int row;
        try {
            row = Integer.parseInt(fox.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid coordinate: " + fox);
        }
        return row == 1;
    }

    public static boolean isHoundWin(String[] players, int dim) {
        if (players == null) {
            throw new NullPointerException();
        }
        if (players.length == 0) {
            throw new IllegalArgumentException("Received empty player array.");
        }
        checkDim(dim);
        int[] positions = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            positions[i] = toBoardIndex(players[i], dim);
        }
        int[] neighs = validNeighbours(positions[positions.length - 1], dim);
        for (int i = 0; i < neighs.length; i++) {
            boolean found = false;
            for (int j = 0; j < positions.length - 1; j++) {
                if (positions[j] == neighs[i]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public static int minDim(String[] players) {
        int dim = (players.length - 1) * 2;
        for (int i = 0; i < players.length; i++) {
            int d = minDim(players[i]);
            if (d > dim) {
                dim = d;
            }
        }
        return dim;
    }

    private static int minDim(String player) {
        if (player == null) {
            throw new NullPointerException();
        }
        if (player.length() < 2) {
            throw new IllegalArgumentException("Given coordinate too short: " + player);
        }
        int row = Integer.parseInt(player.substring(1));
        int col = toColIndex(player.charAt(0)) + 1;
        if (row > col) {
            return row;
        }
        return col;
    }

    private static int[] validNeighbours(int pos, int dim) {
        int row = pos % dim;
        int col = pos / dim;
        int[] neighs = new int[4];
        int count = 0;
        if (row > 0 && col > 0) {
            neighs[count] = (col - 1) * dim + row - 1;
            count++;
        }
        if (row > 0 && col < dim - 1) {
            neighs[count] = (col - 1) * dim + row + 1;
            count++;
        }
        if (row < dim - 1 && col > 0) {
            neighs[count] = (col + 1) * dim + row - 1;
            count++;
        }
        if (row < dim - 1 && col < dim - 1) {
            neighs[count] = (col + 1) * dim + row + 1;
            count++;
        }
        if (count < 4) {
            int[] nn = new int[count];
            for (int i = 0; i < count; i++) {
                nn[i] = neighs[i];
            }
            return nn;
        }
        return neighs;
    }
}
