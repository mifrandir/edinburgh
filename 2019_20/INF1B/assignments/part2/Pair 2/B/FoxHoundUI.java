import javax.swing.*;
import java.awt.*;
import java.io.DataInput;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Objects;

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
    /**Menu entry to select a save action. */
    public static final int MENU_SAVE = 2;
    /**Menu entry to select a load action. */
    public static final int MENU_LOAD = 3;
    /** Menu entry to terminate the program. */
    public static final int MENU_EXIT = 4;

    /**
     * Formats an array of players into a board of a given dimension to be displayed on the console
     *
     * @param players the coordinates of all the players
     * @param dimension the dimension of the board
     * @throws NullPointerException if the players array is null or contains a null element
     * @throws IllegalArgumentException if the dimension isn't within the valid range or if any of the players coordinates aren't valid for the board size
     */
    public static void displayBoard(String[] players, int dimension) {
        FoxHoundUtils.isValidPlayers(players);
        FoxHoundUtils.isValidDim(dimension);
        if(FoxHoundUtils.validCoordinates(players,dimension) == false)
            throw new IllegalArgumentException("ERROR: A players coordinate is not on the board");

        String [] board = new String [dimension+4];
        // get just the basic board with only dots, F and H's in the correct positions
        String [] rowsWithoutNumbers = getBasicBoard(players,dimension);
        // get the header and footer of the board
        String boardLetters = FormatBoardLetters(dimension);

        // format them all together
        board = AddRowsToBoard(board,rowsWithoutNumbers,boardLetters,dimension);

        PrintStringArray(board);
    }

    /**
     * Gets an array containing the rows of the board in their most basic form i.e. with a dot, F, or H at each position
     *
     * @param players the coordinates of all the players
     * @param dimension the dimension of the board
     * @return an array, each element of which represents a row of the board without the numbers added onto each end
     */
    public static String[] getBasicBoard(String [] players, int dimension){
        String [] rowsWithoutNumbers = new String [dimension];
        Arrays.fill(rowsWithoutNumbers,"");
        //iterate through each row
        for(int i = 1; i <= dimension; i++) {
            //iterate through each column
            for (int j = 0; j < dimension; j++) {
                String currentCoordinate = Character.toString('A' + j) + i;
                boolean playerFound = CheckForPlayer(currentCoordinate,players);
                // if the current coordinate in the iteration is in the players array
                if(playerFound) {
                    // set this coordinate to be the appropriate character
                    if (FoxHoundUtils.isFox(currentCoordinate, players))
                        rowsWithoutNumbers[i-1] += FoxHoundUtils.FOX_FIELD;
                    else
                        rowsWithoutNumbers[i-1] += FoxHoundUtils.HOUND_FIELD;
                // otherwise, set it to be a dot
                }else
                    rowsWithoutNumbers[i-1] += '.';
            }
        }
        return rowsWithoutNumbers;
    }

    /**
     * Checks if a player is at a given coordinate
     *
     * @param playerCoord the coordinate being checked against players
     * @param players the coordinates of all the players
     * @return true if playerCoord is an element of players, false if not
     */
    public static boolean CheckForPlayer(String playerCoord, String[] players){
        boolean playerFound = false;
        for (int k = 0; k < players.length; k++) {
            if (players[k].equals(playerCoord))
                playerFound = true;
        }
        return playerFound;
    }

    /**
     * Gets a string containing the letters used on the board for use as a header and footer
     *
     * @param dimension the dimension of the board
     * @return a string to be used as the header and footer of the board
     */
    public static String FormatBoardLetters(int dimension){
        String boardLetters = "";
        // add the spacing at the start, relative to the dimension
        if(dimension >9)
            boardLetters = "   ";
        else
            boardLetters = "  ";

        // add all the letters, relative to the dimension
        for(int i = 0; i < dimension;i++){
            boardLetters += Character.toString('A' + i);
        }
        // add the spacing at the end, relative to the dimension
        if(dimension >9)
            boardLetters += "   ";
        else
            boardLetters += "  ";

        return boardLetters;
    }

    /**
     * Brings all the formatted and basic components of the board together to give an array containing the rows of the final
     * board to be displayed
     *
     * @param board the array containing the formatted rows of the final board to be displayed
     * @param rowsWithoutNumbers the array containing the basic rows of the board
     * @param boardLetters the header/footer of the board
     * @param dimension the dimension of the board
     * @return the board array containing all the properly formatted rows in the correct order
     */
    public static String[] AddRowsToBoard (String[] board, String [] rowsWithoutNumbers, String boardLetters, int dimension){
        // add the header and footer with appropriate line spacing
        board[0] = boardLetters;
        board[board.length-1] = boardLetters;
        board[1]= "";
        board[board.length-2] = "";
        // fill the rest of the board array with the basic rows of the board, properly formatted with numbers at the side
        for(int i = 2; i < board.length-2; i++) {
            //format numbers according to dimension
            if (dimension > 9 && i - 1 < 10)
                board[i] = String.valueOf(0) + (i - 1) + " " + rowsWithoutNumbers[i - 2] + " " + String.valueOf(0) + (i - 1);
            else board[i] = (i-1)+" "+rowsWithoutNumbers[i-2]+" "+(i-1);
        }
        return board;
    }

    /**
     * Prints each element of an array on separate lines
     *
     * @param array the array to be printed
     */
    public static void PrintStringArray (String [] array){
        for(int i = 0; i < array.length; i++)
            System.out.println(array[i]);
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
        if (figureToMove != FoxHoundUtils.FOX_FIELD 
         && figureToMove != FoxHoundUtils.HOUND_FIELD) {
            throw new IllegalArgumentException("Given figure field invalid: " + figureToMove);
        }

        String nextFigure = 
            figureToMove == FoxHoundUtils.FOX_FIELD ? "Fox" : "Hounds";

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
     * Prompt the user for a pair of coordinates and check that they lie on a board of a given dimension
     *
     * @param dim the dimension of the board
     * @param test_in a scanner used to read the users input
     * @return coordinates inputted by the user
     * @throws NullPointerException if the scanner is null
     * @throws IllegalArgumentException if the dimension does not fall within the valid range
     */
    public static String[] positionQuery(int dim, Scanner test_in) {

        FoxHoundUtils.isValidDim(dim);
        FoxHoundUtils.isValidScanner(test_in);
        boolean accepted = false;
        String [] coordinates = new String [2];

        while(!accepted) {
            try {
                // prompt the user for a pair of coordinates within the valid range of the board associated with the dimension
                System.out.println("Provide origin and destination coordinates.");
                System.out.printf("Enter two positions between %s-%s:\n\n",getLeastCoordinate(dim),getMaxCoordinate(dim));
                int counter = 0;
                boolean tooManyCoords = false;
                String coords = "";
                //read the users input, if they have entered more than one line flag this for use later
                while(test_in.hasNextLine()) {
                    if(counter <1) {
                        coords += test_in.nextLine();
                        counter++;
                    }
                    else{
                        tooManyCoords = true;
                        break;
                    }
                }
                String [] coordsToTest = coords.split(" ");
                Arrays.fill(coordinates,"");

                //if they have entered more than 2 coordinates or more than one line, return an error and repeat the loop
                if(coordsToTest.length > 2 || tooManyCoords) {
                    accepted = false;
                    System.err.print("ERROR: Please enter valid coordinate pair separated by space.");
                }

                // otherwise if the coordinates are valid for the dimension, update the array to be returned to contain them
                else if (coordMatchesDim(coordsToTest[0], dim) && coordMatchesDim(coordsToTest[1], dim)) {
                    coordinates[0] = coordsToTest[0];
                    coordinates[1] = coordsToTest[1];
                    accepted = true;
                    break;
                }else
                    System.err.print("ERROR: Please enter valid coordinate pair separated by space.");
            }
            catch(Exception e){
                System.out.println("ERROR: Please enter valid coordinate pair, separated by a space.\n");
            }

        }
        return coordinates;
    }

    /**
     * Get the coordinate at the top left of the board in the correct format
     *
     * @param dim the dimension of the board
     * @return "A1" in the correct format for the dimension
     */
    public static String getLeastCoordinate(int dim){
        String result = "";
        if(dim <= 9)
            result = "A1";
        else if(dim >9)
            result = "A01";
        return result;
    }

    /**
     * Get the coordinate at the bottom right of the board in the correct format
     *
     * @param dim the dimension of the board
     * @return the maximum possible coordinate (ie the bottom right coordinate) in the correct format for the dimension
     */
    public static String getMaxCoordinate(int dim){
        String result = "";
        result = Character.toString('A'+(dim-1)) + dim;
        return result;
    }

    /**
     * Check if a coordinate lies on a board of a specified dimension
     *
     * @param coord a string containing the coordinate to be tested
     * @param dim the dimension of the board
     * @return true if this coord is contained on a board of the given dimension, false if not
     */
    public static boolean coordMatchesDim(String coord, int dim){
        boolean result;

        //if the row and column of the coordinate are on the board, return true
        if(validRow(dim, (""+coord.charAt(0))) && validCol (dim, coord.substring(1)))
            result = true;
        else
            result = false;

        return result;
    }

    /**
     * Check if a row lies on a board of a given dimension
     *
     * @param dim the dimension of the board
     * @param row the row being checked
     * @return true if the row is on a board of size dim, false if not
     */
    public static boolean validRow(int dim, String row){
        boolean result = false;
        //check each row on the board and see if it matches the given one
        for(int i = 0; i < dim; i++ ){
            if(row.toUpperCase().equals(Character.toString('A'+i)))
                result = true;
        }
        return result;
    }

    /**
     * Check if a column lies on a board of a given dimension
     *
     * @param dim the dimension of the board
     * @param col the column being checked
     * @return true if the row is on a board of size dim, false if not
     */
    public static boolean validCol(int dim, String col){
        boolean result = false;
        //iterate through each column
        for(int i = 1; i<= dim; i++){
            //check if the column matches the col parameter in the correct format for the dimension
            if(dim <=9 && col.equals(""+i))
                result = true;
            else if(dim > 9){
                if(i <=9 && col.equals("0"+i))
                    result = true;
                else if( i >9 && col.equals(""+i))
                    result = true;
            }
        }
        return result;
    }

    /**
     * Prompt the user to enter a file path and read this input
     *
     * @param test_in a scanner used to read the users input from the console
     * @return their input as a Path type
     */
    public static Path fileQuery(Scanner test_in) {
        FoxHoundUtils.isValidScanner(test_in);

        System.out.println("\nEnter file path:\n");
        Path path = Paths.get(test_in.next());
        return path;
    }
}







