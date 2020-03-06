
/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions to check the state of the game
 * board and validate board coordinates and figure positions.
 */
public class FoxHoundUtils {
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
    /**
     * Array containing the ALPHABET
     */
    public static final char[] ALPHABET = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * Array containing potential fox positions
     */
    public static final char[] FOXPOS = new char[]{'C', 'D', 'C', 'D', 'E', 'F', 'E', 'F', 'G', 'H', 'G', 'H', 'I', 'J', 'I', 'J', 'K', 'L', 'K', 'L', 'M', 'N', 'M'};


    /**
     * This method takes a dimension and generates the starting positions of hounds and the fox
     * @param dimension dimension of board
     * @return returns a string array with positions of hound and the fox position at the end
     * @throws IllegalArgumentException if dimension is not valid
     */
    public static String[] initialisePositions(int dimension) {
        int nochars = ((dimension / 2) + 1);
        if (dimension < MIN_DIM || dimension > MAX_DIM) {
            throw new IllegalArgumentException("Dimension is invalid!");
        }

        String[] positions = new String[(nochars)];
        char counter = 0;
        for (int i = 0; i < nochars - 1; i++) {
            char letter = (char) ('B' + (2 * counter));
            positions[i] = letter + "1";
            counter++;
        }
        char letterfox = FOXPOS[dimension - 4];
        positions[nochars - 1] = letterfox + Integer.toString(dimension);
        return positions;
    }

    /**
     * This method takes a coord of form (x,y) and converts it to one of the form A1
     * @param x x coordinate
     * @param y y coordinate
     * @return returns a board coordinate (ie A1 or G8)
     */
    public static String convertToBoard(int x, int y) {
        String Board = String.valueOf((char) ('A' + x));
        Board += y;
        return Board;
    }

    /**
     * This method takes a board coord of form A1 and returns if that is valid for the dimension given
     * @param input Coordinate string in board coord form
     * @param dimension dimension of board
     * @return
     */
    public static boolean isCoordValid(String input, int dimension) {
        boolean flag = false;
        for (int i = 0; i < dimension; i++) {
            if (input.charAt(0) == ALPHABET[i]) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }
        if (flag == true) {
            if (dimension < 10) {
                if (input.charAt(1) < 1 || input.charAt(1) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else if (input.charAt(1) == 1) {
                if (10 + input.charAt(2) < 10 || 10 + input.charAt(2) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            } else if (input.charAt(1) == 2) {
                if (20 + input.charAt(2) < 20 || 20 + input.charAt(2) > dimension) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * This method takes in the following parameters and determines if it's a valid move (origin -> destination)
     * @param dimension dimension of board
     * @param players String array of player positions
     * @param figure figure that's being moved 'F' for fox or 'H' for hound
     * @param origin the Board coord that the figure is currently in
     * @param destination the Board coord that the figure wants to move to
     * @return returns a boolean true if the move is valid, otherwise false
     * @throws NullPointerException if players array is null
     * @throws IllegalArgumentException if any other input is invalid
     */
    public static boolean isValidMove(int dimension, String[] players, char figure, String origin, String destination) {
        if (players == null) {
            throw new NullPointerException("Players cannot be Null!");
        }

        if (figure != 'F' && figure != 'H') {
            throw new IllegalArgumentException("Figure is not F or H!");
        }

        if (dimension < MIN_DIM || dimension > MAX_DIM) {
            throw new IllegalArgumentException("Dimension is invalid!");
        }

        if (isCoordValid(origin, dimension)) {
            throw new IllegalArgumentException("Origin is not on board!");
        }

        if (isCoordValid(destination, dimension)) {
            throw new IllegalArgumentException("Destination is not on board!");
        }


        boolean flag = false;
        if (figure == 'F') {
            if (origin.equals(players[players.length - 1])) {
                flag = true;
            }
        } else if (figure == 'H') {
            for (int i = 0; i < players.length - 1; i++) {
                if (origin.equals(players[i])) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag == false) {
            return false;
        }


        for (int i = 0; i < (players.length); i++)
            if (players[i].equals(destination)) {
                return false;
            }

        int num = 0;
        int num2 = 0;
        char d = destination.charAt(0);
        if (destination.length() == 2) {
            num = destination.charAt(1);
        } else {
            String nums = destination.charAt(1) + "" + destination.charAt(2);
            num = Integer.parseInt(nums);
        }
        char o = origin.charAt(0);
        if (origin.length() == 2) {
            num2 = origin.charAt(1);
        } else {
            String nums2 = destination.charAt(1) + "" + destination.charAt(2);
            num2 = Integer.parseInt(nums2);
        }

        if (d != o + 1 && d != o - 1) {
            return false;
        }
        if (num != num2 + 1 && num != num2 - 1) {
            return false;
        }
        return true;
    }

    /**
     *  This function checks if the fox has won
     * @param fox position of fox
     * @return returns a boolean true if the fox has won
     * @throws NullPointerException if the fox coord is null
     */
    public static boolean isFoxWin(String fox) {
        if (fox == null) {
            throw new NullPointerException("Fox cannot be null!");
        }
        boolean flag = false;
        if (fox.length() == 2) {
            if (fox.charAt(1) == '1') {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     *  This function checks if the hounds have won (currently doesn't work)
     * @param players array of player positions in board coord form
     * @param dimension dimension of array
     * @return returns a boolean true if the hounds have won
     * @throws NullPointerException if players is null
     * @throws IllegalArgumentException if the dimension is invalid
     */
    public static boolean isHoundWin(String[] players, int dimension) {
        if (players == null) {
            throw new NullPointerException("Players cannot be null!");
        }
        if (dimension > MAX_DIM || dimension < MIN_DIM) {
            throw new IllegalArgumentException("Dimension is invalid!");
        }
        boolean flag = false;
        int counter = 0;
        String foxpos = players[players.length - 1];
        if (foxpos.length() == 2) {
            int number = foxpos.charAt(1);
            char letter = foxpos.charAt(0);
            String destination1 = (String.valueOf((char)(letter - 1)) + (number + 1));

            String destination2 = (String.valueOf((char)(letter - 1)) + (number + 1));

            String destination3 = (String.valueOf((char)(letter - 1)) + (number + 1));

            String destination4 = (String.valueOf((char)(letter - 1)) + (number + 1));

            if (!(isCoordValid(destination1, dimension))) {
                counter ++;
            }
            if (!(isCoordValid(destination2, dimension))) {
                counter ++;
            }
            if (!(isCoordValid(destination3, dimension))) {
                counter++;
            }
            if (!(isCoordValid(destination4, dimension))) {
                counter++;
            }

            for (int i = 0; i < (players.length-1); i++)
                if (players[i].equals(destination1)) {
                    counter++;
                }
            for (int i = 0; i < (players.length-1); i++)
                if (players[i].equals(destination2)) {
                    counter++;
                }
            for (int i = 0; i < (players.length-1); i++)
                if (players[i].equals(destination3)) {
                    counter++;
                }
            for (int i = 0; i < (players.length-1); i++)
                if (players[i].equals(destination4)) {
                    counter++;
                }


            if (counter > 3) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }
}