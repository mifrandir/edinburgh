import java.util.Arrays;
import java.util.Scanner;

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


    /**
     * Checks if a given dimension lies within the valid range
     * @param dim the dimension to be checked
     * @throws IllegalArgumentException if the dimension lies outside the range
     */
    public static void isValidDim (int dim){
        if(dim <4 || dim > 26)
            throw new IllegalArgumentException("ERROR: Dimension must be between 4 and 26 inclusive.");
    }

    /**
     * Checks that a given players array does not throw any exceptions
     *
     * @param players the players array to be checked
     * @throws NullPointerException if the array is null or contains a null element
     */
    public static void isValidPlayers(String [] players){
        if(players == null)
            throw new NullPointerException("ERROR: Players array cannot be null");
        if(elemsNotNull(players) == false)
            throw new NullPointerException("ERROR: Players array cannot contain a null element");
    }

    /**
     * Checks that a given scanner is valid
     *
     * @param sc the scanner to be checked
     * @throws NullPointerException if the scanner is null
     */
    public static void isValidScanner(Scanner sc){
        if(sc == null)
            throw new NullPointerException("ERROR: Scanner cannot be null");
    }

    /**
     * Sets up the initial positions of all players according to the dimension of the board
     *
     * @param dimension the dimension of the board
     * @return an array containing the initial positons of all players
     * @throws IllegalArgumentException if the dimension does not lie in the valid range
     */
    public static String[] initialisePositions(int dimension) {
        isValidDim(dimension);

        int noOfHounds;
        int foxLetterIndex;

        //calculate the position of the fox and number of hounds depending on the dimension
        if (dimension % 2 == 0) {
            noOfHounds = dimension / 2;
            if ((dimension / 2) % 2 == 0)
                foxLetterIndex = (dimension / 2);
            else
                foxLetterIndex = (dimension / 2) - 1;
        } else {
            noOfHounds = (dimension - 1) / 2;
            if ((noOfHounds - 1) % 2 == 0)
                foxLetterIndex = noOfHounds;
            else
                foxLetterIndex = noOfHounds + 1;
        }

        String[] coordinates = new String[(noOfHounds + 1)];

        // get the coordinates of the hounds
        for (int i = 0; i < coordinates.length - 1; i++) {
            coordinates[i] = Character.toString('B' + (2 * i)) + 1;
        }

        // add the coordinate of the fox
        coordinates[coordinates.length - 1] = Character.toString('A' + foxLetterIndex) + dimension;

        // format the coordinates properly if the dimension is sufficiently large
        if (dimension >= 10) {
            for (int i = 0; i < coordinates.length; i++) {
                if (coordinates[i].substring(1).length() == 1)
                    coordinates[i] = coordinates[i].charAt(0) + "0" + coordinates[i].substring(1);
            }
        }

        return coordinates;
    }

    /**
     * Set the dimension to be the default value if the given dimension lies outside the range
     *
     * @param dimension the dimension being checked
     * @return the dimension if valid, the default dimension if not
     */
    public static int validDimension(int dimension) {
        if (dimension < FoxHoundUtils.MIN_DIM || dimension > FoxHoundUtils.MAX_DIM)
            return FoxHoundUtils.DEFAULT_DIM;
        else
            return dimension;
    }

    /**
     * Check if a given coordinate is that of the fox
     *
     * @param coordinate the coordinate being checked
     * @param players the coordinates of all players
     * @return true if the fox is at the given coordinate, false if not
     */
    public static boolean isFox(String coordinate, String[] players) {
        boolean fox = false;
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(coordinate)) {
                //fox should always be at the end of the players array
                if (i == players.length - 1)
                    fox = true;
                else
                    fox = false;
            }
        }
        return fox;
    }

    /**
     * Checks if the fox has won the game
     *
     * @param foxPos the current coordinate of the fox
     * @return true if the fox has won, false if not
     * @throws NullPointerException if the foxPos given is null
     */
    public static boolean isFoxWin(String foxPos) {
        //handle errors
        if (foxPos == null)
            throw new NullPointerException("ERROR: A fox position is required.");
        if (foxPos.length() < 2 || foxPos.length() > 3)
            return false;

        boolean result = false;

        // if the fox is in the top row, it wins
        if (foxPos.substring(1).equals("1") || foxPos.substring(1).equals("01"))
            result = true;
        else
            result = false;

        return result;
    }

    /**
     * Checks if the hounds have won the game
     *
     * @param players the coordinates of all the players
     * @param dimension the dimension of the board
     * @return true if the hounds have won, false if not
     * @throws NullPointerException if the players array is null or contains a null element
     * @throws IllegalArgumentException if the given dimension lies outside the valid range or if any coordinates in the players
     * array do not lie on a board with the given dimension
     */
    public static boolean isHoundWin(String[] players, int dimension) {
        //handle errors
        isValidDim(dimension);
        isValidPlayers(players);
        if(FoxHoundUtils.validCoordinates(players,dimension) == false)
            throw new IllegalArgumentException("ERROR: A players coordinate is not on the board");

        Character[] boardLetters = getBoardLetters(dimension);
        String[] boardNumbers = getBoardNumbers(dimension);

        //get all the columns and rows the fox can move to from its current positions
        Character[] destColumns = getDestinationColumns(dimension, players[players.length - 1], boardLetters);
        String[] destRows = getDestRowsFox(dimension, boardNumbers, players[players.length - 1]);

        //combine these to get an array of possible coordinates the fox could move to
        String[] possibleDestinations = getDestinations(destColumns, destRows);

        boolean result = true;

        //if any single move the fox can make is valid then the hounds have not won, otherwise they have
        for (int i = 0; i < possibleDestinations.length; i++) {
            if (isNotPlaceHolder(possibleDestinations[i], dimension)) {
                if (isValidMove(dimension, players, 'F', players[players.length - 1], possibleDestinations[i]) == true)
                    result = false;
            }
        }

        return result;
    }

    /**
     * Check that a coordinate is not a place holder coordinate, used to fill every element of an array in certain situations
     *
     * @param coord the coordinate being checked
     * @param dim the dimension of the board
     * @return true if it is not a place holder, false otherwise
     */
    public static boolean isNotPlaceHolder(String coord, int dim) {
        boolean result = false;
        // place holder for dimensions less than 10 if the row = 0
        if (dim < 10) {
            if (Character.toString(coord.charAt(1)).equals("0") == false)
                result = true;
        // all non place holders for dimensions >= 10 are 3 characters long, place holders are 2
        } else if (dim >= 10) {
            if (coord.length() == 3)
                result = true;
        }
        return result;
    }

    public static String[] updatePlayers(String[] players, String origin, String dest) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(origin))
                players[i] = dest;
        }
        return players;
    }

    /**
     * Checks if the move being made is valid
     *
     * @param dim the dimension of the board
     * @param players the positions of all players
     * @param figure the player moving
     * @param origin the original positon of the player
     * @param dest the coordinate they are trying to reach
     * @return true if the move is valid, false otherwise
     * @throws IllegalArgumentException if the the figure provided is not that of a fox or hound or if the dimension
     * given does not lie in the valid range
     * @throws NullPointerException if the players array, an element of the players array, origin or destination is null
     */
    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest) {
        //handle errors
        isValidDim(dim);
        isValidPlayers(players);
        if (Character.toUpperCase(figure) != 'F' && Character.toUpperCase(figure) != 'H')
            throw new IllegalArgumentException("ERROR: Figure must be 'F' or 'H'.");
        if (origin == null)
            throw new NullPointerException("ERROR: Origin coordinate must be provided.");
        if (dest == null)
            throw new NullPointerException("ERROR: Destination coordinate must be provided");

        //if all criteria for a valid move are satisfied, return true
        if (onlyOneMove(origin, dest) && isFoxIfFirst(players, dim, figure) && isValidDestFromOrig(figure, dim, origin, dest) &&
                destNotOccupied(players, dest) && positionMatchesOrigin(players, figure, origin) && validCoordinates(origin, dest))
            return true;
        else
            return false;
    }

    /**
     * Checks that coordinates aren't empty
     *
     * @param origin the original position
     * @param dest the coordinate being moved to
     * @return true if both non empty, false otherwise
     */
    public static boolean validCoordinates(String origin, String dest) {
        boolean result = false;
        if (origin.equals("") == false && dest.equals("") == false)
            result = true;
        return result;
    }

    /**
     * Checks if the specified figure is at the origin
     *
     * @param players locations of all players
     * @param figure the player who is taking their turn
     * @param origin the coordinate being checked
     * @return true if the given figure is at the origin, false if not
     */
    public static boolean positionMatchesOrigin(String[] players, char figure, String origin) {
        boolean result = false;
        // fox should always be at the end of the players array
        if (figure == 'F' && origin.equals(players[players.length - 1]))
            result = true;
        // if hound is moving, check if first n-1 elements of an array of length n contain the origin
        else if (figure == 'H') {
            for (int i = 0; i < players.length - 1; i++) {
                if (players[i].equals(origin))
                    result = true;
            }
        }
        return result;
    }

    /**
     * Check that the user is not trying to make more than one move at once
     *
     * @param origin the current position of the piece being moved
     * @param dest the coordinate the user wishes to move it to
     * @return true if the user is only trying to make one move, false otherwise
     */
    public static boolean onlyOneMove(String origin, String dest) {
        boolean result = false;
        // if the coordinates are both of the same length and either or length 2 or 3, this constitutes a single pair of coordinates
        // for all possible dimensions
        if ((origin.length() == 2 && dest.length() == 2) || (origin.length() == 3 && dest.length() == 3))
            result = true;
        else
            result = false;
        return result;
    }

    /**
     * Checks that the if its the first move of the game, the fox is moving
     *
     * @param players current position of all players
     * @param dim dimension of the board
     * @param figure figure being moved
     * @return if it is the first turn and it is the fox, return true and return false if it is the hound
     *         return true if not first turn
     */
    public static boolean isFoxIfFirst(String[] players, int dim, char figure) {
        boolean result = false;
        // check if its the first turn
        if (players == initialisePositions(dim)) {
            switch (Character.toUpperCase(figure)) {
                case 'F':
                    result = true;
                case 'H':
                    result = false;
            }
        } else
            result = true;

        return result;
    }

    /**
     * Check if the destination can be reached from the origin coordinate
     *
     * @param figure the player being moved
     * @param dim the dimension of the board
     * @param origin the origin coordinate
     * @param dest the destination coordinate
     * @return true if the destination can be reached from the origin, false otherwise
     */
    public static boolean isValidDestFromOrig(char figure, int dim, String origin, String dest) {
        boolean result;

        Character[] boardLetters = getBoardLetters(dim);
        String[] boardNumbers = getBoardNumbers(dim);

        //get the columns the player can move to
        Character[] destLetters = getDestinationColumns(dim, origin, boardLetters);
        String[] destNumbers = new String[2];

        //get the rows the player can move to
        //dependent on the player type as different players can move different directions
        switch (figure) {
            case 'F':
                destNumbers = getDestRowsFox(dim, boardNumbers, origin);
                break;
            case 'H':
                destNumbers = getDestRowsHound(dim, boardNumbers, origin);
                break;
        }

        //array of all possible destination coordinates
        String[] validDestinations = getDestinations(destLetters, destNumbers);
        //check if the given destination is one of the valid ones
        result = checkDestination(validDestinations, dest);
        return result;
    }

    /**
     * Gets all the possible columns on a board of a given dimension
     *
     * @param dim the dimension of the board
     * @return an array of characters representing the board columns
     */
    public static Character[] getBoardLetters(int dim) {
        Character[] letters = new Character[dim];
        //cycle through the alphabet from A to x where x is the dim-1 th letter of the alphabet
        for (int i = 0; i < dim; i++) {
            letters[i] = (char) ('A' + i);
        }
        return letters;
    }

    /**
     * Gets all the possible rows on a board of a given dimension
     *
     * @param dim the dimension of the board
     * @return an array containing all the possible rows on the board
     */
    public static String[] getBoardNumbers(int dim) {
        String[] numbers = new String[dim];
        for (int i = 0; i < dim; i++) {
            //format the numbers according to the dimension of the board
            if (dim < 10 || (dim >= 10 && i >= 9))
                numbers[i] = Integer.toString(i + 1);
            else if (dim >= 10 && i <= 8)
                numbers[i] = Integer.toString(0) + Integer.toString(i + 1);
        }
        return numbers;
    }

    /**
     * Gets all the columns a player can reach from an origin coordinate
     *
     * @param dim the dimension of the board
     * @param origin the original position of the players
     * @param boardLetters all the columns on the board
     * @return the letters representing the columns the player can reach
     */
    public static Character[] getDestinationColumns(int dim, String origin, Character[] boardLetters) {
        Character[] destLetters = new Character[2];

        for (int i = 0; i < dim; i++) {
            //if the origin is in the current column
            if (boardLetters[i] == origin.charAt(0)) {
                //if the current column isn't at the edge of the board, can reach columns to either side
                if (i >= 1 && i <= dim - 2) {
                    destLetters[0] = boardLetters[i - 1];
                    destLetters[1] = boardLetters[i + 1];
                // if at the left edge, can reach column to right
                } else if (i == 0) {
                    destLetters[0] = boardLetters[i + 1];
                    destLetters[1] = '-';
                // if at right edge, can reach column to left
                } else if (i == dim - 1) {
                    destLetters[0] = boardLetters[i - 1];
                    destLetters[1] = '-';
                }
            }
        }
        return destLetters;
    }

    /**
     * Gets an array of destination coordinates from an array of rows and one of columns
     *
     * @param destLetters the columns that can be reached
     * @param destNumbers the rows that can be reached
     * @return an array of coordinates that can be reached
     */
    public static String[] getDestinations(Character[] destLetters, String[] destNumbers) {
        String[] validDestinations = new String[4];
        Arrays.fill(validDestinations, "");

        int k = 0;
        //iterate through each row and for each column, add a coordinate combining the two to the validDestinations array
        for (int i = 0; i < destLetters.length; i++) {
            for (int j = 0; j < destNumbers.length; j++) {
                validDestinations[k] = String.valueOf(destLetters[i]) + destNumbers[j];
                k++;
            }
        }
        return validDestinations;
    }

    /**
     * Get the rows that can be reached if the player is a fox
     *
     * @param dim the dimension of the board
     * @param boardNumbers all rows on the board
     * @param origin the current location of the fox
     * @return an array of rows the fox can reach
     */
    public static String[] getDestRowsFox(int dim, String[] boardNumbers, String origin) {
        String[] destNumbers = new String[2];
        Arrays.fill(destNumbers, "");
        for (int i = 0; i < dim; i++) {
            // if the current row is the one where the fox is
            if (boardNumbers[i].equals(origin.substring(1))) {
                // if not at the edges of the board, fox can move forward and back
                if (i >= 1 && i <= dim - 2) {
                    destNumbers[0] = boardNumbers[i - 1];
                    destNumbers[1] = boardNumbers[i + 1];
                // if at the top, fox can only move back and second coordinate filled with place holder
                } else if (i == 0) {
                    destNumbers[0] = boardNumbers[1];
                    destNumbers[1] = "0";
                // if at the bottom, fox can only move forward and second coordinate filled with place holder
                } else if (i == dim - 1) {
                    destNumbers[0] = boardNumbers[dim - 2];
                    destNumbers[1] = "0";
                }
            }
        }
        return destNumbers;
    }

    /**
     * Get the rows that the hound can reach from its current position
     *
     * @param dim the dimension of the board
     * @param boardNumbers all the rows on the board
     * @param origin the current position of the hound
     * @return an array containing all rows that the hound can reach
     */
    public static String[] getDestRowsHound(int dim, String[] boardNumbers, String origin) {
        String[] destNumbers = new String[2];
        Arrays.fill(destNumbers, "");

        for (int i = 0; i < dim; i++) {
            // if the hounds current position is in this row
            if (boardNumbers[i].equals(origin.substring(1))) {
                // if the hound isn't at the bottom of the board, it can move forward and fill other coordinate with a place holder
                if (i < dim - 1) {
                    destNumbers[0] = boardNumbers[i + 1];
                    destNumbers[1] = "0";
                // if hound is at bottom of board, it cannot move and fill other coordinate with a place holder
                } else if (i == dim - 1) {
                    destNumbers[0] = boardNumbers[i];
                    destNumbers[1] = "0";
                }
            }
        }
        return destNumbers;
    }

    /**
     * Check if a destination coordinate is valid by comparing against an array of valid destinations
     *
     * @param validDestinations array containing all valid destinations
     * @param dest the coordinate being checked
     * @return true if dest is contained in validDetinations, false otherwise
     */
    public static boolean checkDestination(String[] validDestinations, String dest) {
        boolean valid = false;
        for (int i = 0; i < validDestinations.length; i++) {
            if (dest.equals(validDestinations[i]))
                valid = true;
        }
        return valid;
    }

    /**
     * Check that there are no players in the destination coordinate
     *
     * @param players locations of all players
     * @param dest destination coordinate
     * @return true if the destination is empty, false if not
     */
    public static boolean destNotOccupied(String[] players, String dest) {
        boolean result = true;

        for (int i = 0; i < players.length; i++) {
            // return false if the destination is contained in players
            if (players[i].equals(dest))
                result = false;
        }

        return result;
    }

    /**
     * Checks if the figure provided represents either a fox or a hound
     *
     * @param figure the figure being checked
     * @return true if figure is 'F' or 'H', false otherwise
     */
    public static boolean isValidFigure(char figure) {
        if (Character.toUpperCase(figure) != 'F' && Character.toUpperCase(figure) != 'H')
            return false;
        else
            return true;
    }

    /**
     * Checks that an array does not contain any null elements
     *
     * @param array array to be checked
     * @return false if any single element of the array is null, true otherwise
     */
    public static boolean elemsNotNull(String[] array) {
        boolean result = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)
                result = false;
        }
        return result;
    }

    /**
     * Checks that every coordinate in an array is valid for a given board size
     *
     * @param array coordinates to be checked
     * @param dim size of board
     * @return true if all coordinates in the array lie on the board, false if not
     */
    public static boolean validCoordinates(String[] array, int dim) {
        Character[] letters = getBoardLetters(dim);
        String[] numbers = getBoardNumbers(dim);
        boolean result = true;

        // iterate through all coordinates
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            boolean letterValid = false;
            boolean numValid = false;
            // if the column of the coordinate is valid, flag this
            for (int j = 0; j < letters.length; j++) {
                if (element.charAt(0) == letters[j])
                    letterValid = true;
            }
            // if the row of the coordinate is valid, flag this
            for (int k = 0; k < letters.length; k++) {
                if (element.substring(1).equals(numbers[k]))
                    numValid = true;
            }
            // if either component of the coordinate is invalid, return false otherwise repeat for all other coordinates
            if (letterValid == false || numValid == false)
                result = false;
        }
        // if no invalid coordinates are found, return true
        return result;
    }



}
