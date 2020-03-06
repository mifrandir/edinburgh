import java.util.Arrays;

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

    /** String used to construct player positions.*/
    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // HINT Write your own constants here to improve code readability ...

    /**
     * initializes the original positions at the start of the game.
     * @param dimension An int that specifies dimension of game board.
     * @return string array which specifies positions for all pieces on game board.
     * @throws IllegalArgumentException if dimension value is not between 4 and 26.
     */

    public static String[] initialisePositions(int dimension) {
        if (dimension < 4 || dimension > 26) {
            throw new IllegalArgumentException("Input a dimension that is between 4 and 26.");
        }

        int hound_num = (int) Math.floor((double) dimension / 2); // number of total hounds in game
        String[] positions = new String[hound_num + 1];

        // Initialize hound positions.
        for (int i = 0; i < hound_num; i++) {
            positions[i] = ((char) ('B' + 2 * i)) + "1";
        }
        // Initialize fox position.
        if (dimension % 2 == 0) {
            positions[hound_num] = ((char) ('A' + foxPosEven(dimension)) + Integer.toString(dimension));
        } else {
            positions[hound_num] = ((char) ('A' + foxPosOdd(dimension)) + Integer.toString(dimension));
        }

        return positions;
    }

    /**
     * helper function that determines the alphabetical position of fox in odd dimensions.
     * @param dimension An int that specifies dimension of game board.
     * @return integer which represents the alphabetical position of the fox.
     */
    public static int foxPosOdd(int dimension) {
        if ((dimension / 2) % 2 == 0) {
            return ((dimension / 2) + 1);
        } else {
            return (dimension / 2);
        }
    }

    /**
     * helper function that determines the alphabetical position of fox in even dimensions.
     * @param dimension An int that specifies dimension of game board.
     * @return integer which represents the alphabetical position of the fox.
     */
    public static int foxPosEven(int dimension) {
        if ((dimension / 2) % 2 == 0) {
            return ((dimension / 2));
        } else {
            return (dimension / 2) - 1;
        }
    }

    /**
     * checks if the fox has won the game or not.
     *
     * @param foxPos String which represent the fox's position
     * @return boolean which determines if fox has won or not.
     * @throws NullPointerException if the given String is null
     */
    public static boolean isFoxWin(String foxPos) {
        if (foxPos == null) {
            throw new NullPointerException("String must be inputted.");
        }
        if (foxPos.substring(1).equals("1")) {
            System.out.println("The Fox wins!");
            return true;
        }
        return false;
    }

    /**
     * checks if the hounds have won the game or not.
     *
     * @param players String array which represent all game pieces positions.
     * @param dimension An int that specifies dimension of game board.
     * @return boolean which determines if the hounds have won.
     * @throws IllegalArgumentException if dimension value is not between 4 and 26.
     * @throws NullPointerException if players array is null.
     */
    public static boolean isHoundWin(String[] players, int dimension) {
        if (dimension < 4 || dimension > 26) {
            throw new IllegalArgumentException("Input a dimension that is between 4 and 26.");
        }

        if (players == null) {
            throw new NullPointerException("Null array not allowed.");
        }

        if (!checkValidMove(players, dimension)) {
            System.out.println("The Hounds win!");
            return true;
        }
        return false;
    }

    /**
     * helper function that checks if fox can make any valid move.
     * @param players String array which represent all game pieces positions.
     * @param dimension An int that specifies dimension of game board.
     * @return boolean which represents if fox can make any valid move.
     * @throws NullPointerException if players array is null.
     */
    public static boolean checkValidMove(String[] players, int dimension) {
        if (players == null) {
            throw new NullPointerException("Null array not allowed.");
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 1; j < dimension + 1; j++) {
                String position = alphabet.charAt(i) + Integer.toString(j);
                if (isValidMove(dimension, players, 'F', players[players.length - 1], position)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if a move is valid
     * @param dim An int that specifies dimension of game board.
     * @param players String array which represent all game pieces positions.
     * @param figure the figure type to be checked.
     * @param origin the starting/origin position of the piece to be moved.
     * @param dest the destination position of the piece to be moved.
     * @throws NullPointerException if null is passed for players array, origin, or dest.
     * @throws IllegalArgumentException if a incorrect dimension, figure, or length of player array.
     * @return boolean which determines if move is valid
     */

    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest) {
        if (dim < 4 || dim > 26) {
            throw new IllegalArgumentException("Input a dimension that is between 4 and 26.");
        }
        if (origin == null || dest == null) {
            throw new NullPointerException(("Input a string for both origin and dest."));
        }
        if ((figure != 'H') && (figure != 'F')) {
            throw new IllegalArgumentException(("Input for figure must be either H (hound) or F (fox)."));
        }
        if (players == null) {
            throw new NullPointerException("Null array not allowed.");
        }
        if (players.length == 0) {
            throw new IllegalArgumentException("Arrays of length 0 not allowed.");
        }

        return isPlayers(players, origin) && isEither(players, figure, origin) && checkCollision(players, dest) && onBoard(dim, dest) && isPossible(figure, origin, dest);
    }

    /**
     * helper function that checks if assigned origin is a element of players.
     * @param players String array which represent all game pieces positions.
     * @param origin the starting/origin position of the piece to be moved.
     * @return boolean that checks if origin is in players array.
     * @throws NullPointerException if players array or origin is null.
     */
    public static boolean isPlayers(String[] players, String origin) {
        if (players == null || origin == null) {
            throw new NullPointerException("players and origin arguments cannot be null.");
        }

        boolean check = false;
        for (String player : players) {
            if (player.equals(origin)) {
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * helper function for method isEither that finds index in players array where specified origin is located in.
     * @param players String array which represent all game pieces positions.
     * @param origin the starting/origin position of the piece to be moved.
     * @return int of position of origin in players array.
     * @throws NullPointerException if players array or origin is null.
     */
    public static int inArray(String[] players, String origin) {
        if (players == null || origin == null) {
            throw new NullPointerException("players and origin arguments cannot be null.");
        }

        for (int i = 0; i < players.length; i++)
            if (players[i].equals(origin))
                return i;

        return -1;
    }

    /**
     * helper function that checks if the figure is a hound, it is in first n-1 elements of players,
     * if fox then last element of players array.
     * @param players String array which represent all game pieces positions.
     * @param figure the figure type to be checked.
     * @param origin the starting/origin position of the piece to be moved.
     * @return boolean that checks if origin is in correct location in players array.
     * @throws NullPointerException if players array or origin is null.
     */
    public static boolean isEither(String[] players, char figure, String origin) {
        if (players == null || origin == null) {
            throw new NullPointerException("players and origin arguments cannot be null.");
        }

        if (inArray(players, origin) + 1 < players.length) {
            return figure == 'H';
        } else {
            return figure == 'F';
        }
    }

    /**
     * helper function that checks the move does not go into a position with another piece
     * @param players String array which represent all game pieces positions.
     * @param dest the destination position of the piece to be moved.
     * @return boolean that checks no two pieces occupy same position.
     * @throws NullPointerException if players array or dest is null.
     */
    public static boolean checkCollision(String[] players, String dest) {
        if (players == null || dest == null) {
            throw new NullPointerException("players and origin arguments cannot be null.");
        }

        for (String player : players) {
            if (player.equals(dest)) {
                return false;
            }
        }
        return true;
    }

    /**
     * helper function that checks if new position is on the board
     * @param dim An int that specifies dimension of game board.
     * @param dest the destination position of the piece to be moved.
     * @return boolean that checks new position is on the board.
     * @throws NullPointerException if dest is null.
     *
     */
    public static boolean onBoard(int dim, String dest) {
        if (dest == null) {
            throw new NullPointerException("dest string cannot be null.");
        }

        try {
            boolean alphaPosition = (alphabet.substring(0, dim)).indexOf(dest.charAt(0)) != -1;
            int posIndex = Integer.parseInt(dest.substring(1));
            boolean intPosition = posIndex <= dim && posIndex > 0;

            return alphaPosition && intPosition;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * helper function that checks if a move is possible.
     * @param figure the figure type to be checked.
     * @param origin the starting/origin position of the piece to be moved.
     * @param dest the destination position of the piece to be moved.
     * @return boolean that returns if move is possible.
     * @throws NullPointerException if players origin or dest is null.
     */
    public static boolean isPossible(char figure, String origin, String dest) {
        if (origin == null || dest == null) {
            throw new NullPointerException("dest and origin arguments cannot be null.");
        }

        boolean goRight = dest.charAt(0) == (char) (origin.charAt(0) + 1);
        boolean goLeft = dest.charAt(0) == (char) (origin.charAt(0) - 1);
        boolean goForward = dest.substring(1).equals(Integer.toString(Integer.parseInt(origin.substring(1)) - 1));
        boolean goBackward = dest.substring(1).equals(Integer.toString(Integer.parseInt(origin.substring(1)) + 1));
        if (figure == 'H') {
            return (goBackward && goLeft) || (goBackward && goRight);
        } else {
            return (goBackward && goLeft) || (goBackward && goRight) || (goForward && goLeft) || (goForward && goRight);
        }
    }
}
