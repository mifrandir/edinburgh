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

    /**Places the figures on to the appropriate starting positions.
     *
     * @param players stores the inital positions in an array
     * @return the array containing the initial positions of the figures
     */

    public static String[] initialisePositions(int dimension) {
        if (dimension < 0) {
            throw new IllegalArgumentException("the dimension is negative");
        }
        int hounds = dimension / 2;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String[] players = new String[hounds + 1];
        for (int i = 0; i <= hounds; i++) {
            players[i] = alphabet.charAt(i * 2 + 1) + "1";
        }
        int fox = dimension / 2;
        if ((fox % 2) == 1) {
            fox = fox + 1;
        }
        players[hounds] = alphabet.charAt(dimension / 2) + Integer.toString(dimension);
        return players;
    }

    /**
     * The following functions check wether a move is valid, by inspecting specific criteria.
     *
     * @param dimension The dimensions of the board
     * @param destination The field in the board the figure is being moved to
     * @return Is the criterium satisfied
     *
     */
    public static boolean FieldOnBoard(int dimension, String destination) {
        boolean valid1 =
                (destination.charAt(0)) >= 'A' && (destination.charAt(0)) <= ('A' + dimension);
        /**
         * boolean valid2 = (((int) destination.charAt(1) > 0) && ((int) destination.charAt(1) <
         * dimension + 1) );
         */
        return (valid1);
    }

    /**
     * Checks that the destination is within one diagonal move of the origin
     * @param origin The initial position of the figure
     * @param destination The field in the board the figure is being moved to
     * @return Is the criterium satisfied
     */
    public static boolean OneDiagonal(String origin, String destination, int dimension) {
        String alphabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int temp = 1;
        boolean valid2 = false;
        boolean valid1 = destination.charAt(1) == (char) (origin.charAt(1) + 1)
                || destination.charAt(1) == (char) (origin.charAt(1) - 1);
        for (int i = 0; i < dimension; i++) {
            if (alphabeth.charAt(i) == origin.charAt(0)) {
                temp = i;
            }
        }
        valid2 = (destination.charAt(0) == alphabeth.charAt(temp + 1)
                || destination.charAt(0) + 1 == alphabeth.charAt(temp));
        return (valid1 && valid2);
    }

    /**
     * Checks that if the figure is a hound, the destination is one row below the origin
     * @param origin The initial position of the figure
     * @param destination The field in the board the figure is being moved to
     * @return Is the criterium satisfied
     */

    public static boolean HoundAllowed(String origin, String destination, char figure) {
        boolean valid = false;
        if (figure == 'F') {
            valid = true;
        } else {
            if (destination.charAt(1) == (origin.charAt(1) + 1)) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * Checks that the position of the figure given in origin is the same as the one in players
     * @param players The coordinates of all the figures
     * @param origin The initial position of the figure
     * @return Is the criterium satisfied
     */
    public static boolean CorrectOrigin(
            String[] players, String origin, int dimension, char figure) {
        boolean criterium4 = false;
        if (figure == 'H') {
            for (int i = 0; i < players.length - 1; i++) {
                if ((origin.charAt(0) == players[i].charAt(0))
                        && (origin.charAt(1) == players[i].charAt(1))) {
                    criterium4 = true;
                }
            }
        } else {
            criterium4 = ((origin.charAt(0) == players[players.length - 1].charAt(0))
                    && (origin.charAt(1) == players[players.length - 1].charAt(1)));
        }
        return criterium4;
    }

    /**
     * Checks that the field the figure is moving to is unoccupied
     * @param players The coordinates of all the figures
     * @param destination The field in the board the figure is being moved to
     * @return
     */
    public static boolean FieldEmpty(String[] players, String destination) {
        boolean criterium5 = true;
        for (int i = 0; i < players.length; i++) {
            if ((destination.charAt(0) == players[i].charAt(0))
                    && (destination.charAt(1) == players[i].charAt(1))) {
                criterium5 = false;
            }
        }
        return criterium5;
    }

    /**
     * Joins the functions above into a complete function which checks wether a move is valid or
     * not.
     * @return boolean Is the move valid
     */
    public static boolean isValidMove(
            int dimension, String[] players, char figure, String origin, String destination) {
        boolean crit1 = FieldOnBoard(dimension, destination);
        boolean crit2 = OneDiagonal(origin, destination, dimension);
        boolean crit3 = HoundAllowed(origin, destination, figure);
        boolean crit4 = CorrectOrigin(players, origin, dimension, figure);
        boolean crit5 = FieldEmpty(players, destination);
        return (crit1 && crit2 && crit3 && crit4 && crit5);
    }

    /**Checks is the fox has won (if its at the other side of the board)
     */
    public static boolean isFoxWin(String foxPosition) {
        boolean foxWin = (foxPosition.charAt(1) == '1');
        return foxWin;
    }

    public static boolean isHoundWin(String[] players, int dimension) {
        if (dimension < 0) {
            throw new IllegalArgumentException("the dimension is negative");
        }
        String alphabeth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean houndWin = true;
        int temp = 0;
        String foxPosition = players[players.length - 1];
        String[] surroundingFields = new String[4];
        for (int i = 0; i < dimension; i++) {
            if (alphabeth.charAt(i) == foxPosition.charAt(0)) {
                temp = i;
            }
        }
        surroundingFields[0] =
                (alphabeth.charAt(temp + 1)) + ("" + (foxPosition.charAt(1) + 1 - 48));
        surroundingFields[1] =
                (alphabeth.charAt(temp + 1)) + ("" + (foxPosition.charAt(1) - 1 - 48));
        surroundingFields[2] =
                (alphabeth.charAt(temp - 1)) + ("" + (foxPosition.charAt(1) + 1 - 48));
        surroundingFields[3] =
                (alphabeth.charAt(temp - 1)) + ("" + (foxPosition.charAt(1) - 1 - 48));
        ;
        for (int i = 0; i < 4; i++) {
            houndWin = houndWin
                    && !(FieldOnBoard(dimension, surroundingFields[i])
                            && FieldEmpty(players, surroundingFields[i]));
        }
        return houndWin;
    }
}
