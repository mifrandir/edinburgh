/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */

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

    public static String[] initialisePositions(int dimension) {
        int dim = dimension;
        if (dimension <= 26 && dimension >= 4) {
            dim = dimension;
        } else {
            dim = 8;
        }
        // check if dimension is in range 4-26,if not,dim=8

        int n = (dim / 2) + 1; // number of players
        String[] players = new String[n];
        for (int i = 0; i < n - 1; i++) {
            players[i] = Character.toString('B' + 2 * i) + 1;
        }
        if (dim % 2 == 0) {
            players[n - 1] = Character.toString('A' + (dim / 2)) + dim;
        } else {
            if (((dim - 1) / 2) % 2 == 0) {
                players[n - 1] = Character.toString('A' + ((dim + 1) / 2)) + dim;
            } else {
                players[n - 1] = Character.toString('A' + (dim / 2)) + dim;
            }
        }
        return players;
    }

    //    public static void main(String[]
    //    args){System.out.println(Arrays.toString(initialisePositions(8)));}

    public static boolean isValidMove(
            int dim, String[] players, Character figure, String origin, String destination) {
        String[][] board_coor = new String[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                board_coor[i][j] = (Character.toString('A' + j) + (i + 1));
            }
        }
        int len = players.length;
        boolean valid1 = false;
        boolean valid2 = false;
        boolean valid3 = false;

        if (figure == 'F') {
            if (origin.equals(players[len - 1])) {
                valid1 = true;
            }
        } else if (figure == 'H') {
            for (int i = 0; i < (len - 1); i++) {
                if (origin.equals(players[i])) {
                    valid1 = true;
                }
            }
        }
        // determine if figure is F or H or others.If F : determine if origin is the last element in
        // players,return true. if H : determine if origin is any one of elements in players(but not
        // the last one),return true.

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (destination.equals(board_coor[i][j])) {
                    valid2 = true;
                    for (int c = 0; c < len; c++) {
                        if (destination.equals(players[c])) {
                            valid2 = false;
                        }
                    }
                }
            }
        }
        // determine if the destination is on the board,if so,return true.Then check if destination
        // is one of the coordinates of the figures(element in players)
        if (figure == 'F') {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    for (int k = 0; k < dim; k++) {
                        for (int c = 0; c < dim; c++) {
                            if (origin.equals(board_coor[i][j])
                                    && destination.equals(board_coor[k][c])) {
                                if ((Math.abs(i - k) == 1) && (Math.abs(j - c) == 1)) {
                                    valid3 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (figure == 'H') {
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    for (int k = 0; k < dim; k++) {
                        for (int c = 0; c < dim; c++) {
                            if (origin.equals(board_coor[i][j])
                                    && destination.equals(board_coor[k][c])) {
                                if (((k - i) == 1) && (Math.abs(j - c) == 1)) {
                                    valid3 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return (valid1 && valid2 && valid3);
    }

    public static boolean isHoundWin(Object o, int defaultDim) {
        return false;
    }

    public static boolean isFoxWin(String foxPos) {
        return false;
    }
}
