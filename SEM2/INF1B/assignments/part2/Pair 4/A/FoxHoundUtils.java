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

    /**
     * Converts integer to alphabet, A=1. B=2 etc.
     * @param x Number to convert.
     * @return Alphabet char.
     */
    public static Character posToChar(int x){
        if(x < 1 || x > 26){
            return null;
        }
        return (char)(x + 64); // +64 to get to upper case alphabet part in ASCII.
    }

    /**
     * Converts an alphabet character to a number. A=1 B=2.
     * @param c Uppercase alphabet character.
     * @return It's corresponding position as Integer.
     */
    public static Integer charToPos(char c){
        if(c < 'A' || c > 'Z'){
            return null;
//            throw new IllegalArgumentException("Char isn't upper case in alphabet.");
        }
        return ((int) c) - 64;// -64 to get from upper case alphabet part in ASCII.
    }

    /**
     *  Throws an exception if dimension is out of standard range.
     * @param dimension Dimension to check.
     */
    static void validateDimension(int dimension){
        if(dimension < 4 || dimension > 26){
            throw new IllegalArgumentException("Dimension can't be less than 4 or more than 26.");
        }
    }

    /** Check whether turn char is valid.
     * @param turn Turn to check.
     * @return Whether turn is valid.
     */
    static boolean isValidTurn(char turn){
        return turn == HOUND_FIELD || turn == FOX_FIELD;
    }

    /**
     * Converts x and y coords to string representation.
     * @param x X value.
     * @param y Y value.
     * @return Coords as displayable string.
     */
    static String posToStr(int x, int y){
        Character x_char = posToChar(x);
        if(x_char == null){
            return null;
        }
        return x_char + "" + y;
    }

    /**
     *  Converts string representation to coords.
     * @param str String to convert.
     * @return Coords as an array. [0] for x, [1] for y. Null if invalid.
     */
    static int[] strToPos(String str) {
        if (str.length() < 2){ // No valid is string is less than 2.
            return null;
        }
        char x_char = str.charAt(0); // X is always one character.
        String y_str = str.substring(1); // Rest of string for Y.
        Integer x = charToPos(x_char);
        if (x == null){
            return null;
        }
        int y;
        try {
            y = Integer.parseInt(y_str); // Try to convert remained to Integer.
        } catch (NumberFormatException e) {
            return null; // If any exception, casting failed.
        }
        int[] position = {x,y}; // Construct returning array with x and y.
        return position;
    }

    /**
     * Initializes the starting positions for all board pieces.
     * @param dimension Board size.
     * @return Array containing coords of all pieces.
     */
    public static String[] initialisePositions(int dimension) {
        FoxHoundUtils.validateDimension(dimension);
        int enemy_count = dimension / 2; // Rounds down.

        String[] output = new String[enemy_count + 1]; // One extra slot for fox.

        for (int i = 0; i < enemy_count; i++){ // Add correct number of hounds.
            output[i] = posToStr(2 + i * 2, 1);
        }
        int white_x = dimension / 2 + 1; // Calculate fox's position.

        output[output.length - 1] = posToStr(white_x, dimension);

        return output;
    }

    /**
     *  Checks a tile is on a valid tile on the board.
     * @param dim Board size.
     * @param position Tile coords.
     * @return Whether tile is on board.
     */
    static boolean tileIsOnBoard(int dim, int[] position){
        validateDimension(dim);
        int x = position[0];
        int y = position[1];
        return x <= dim && y <= dim && x >= 1 && y >= 1 // On board.
                && (x + y) % 2 == 1; // On black square (odd total).
    }

    /**
     * Calculates whether the fox has won.
     * @param players All players.
     * @return Whether fox has won.
     */
    public static boolean isFoxWinPlayers(String[] players){
        if (players.length < 1){
            return false;
        }

        return isFoxWin(players[players.length - 1]);
    }

    /**
     * Checks whether fox has won.
     * @param foxPos Fox position.
     * @return Whether fox has won.
     */
    public static boolean isFoxWin(String foxPos){
        int[] position = FoxHoundUtils.strToPos(foxPos);
        if (position == null){
            throw new IllegalArgumentException("Invalid fox position.");
        }
        return position[1] == 1;
    }

    /**
     *  Checks whether hound has won.
     * @param players All players.
     * @param dimension Board size.
     * @return Whether hound has won.
     */
    public static boolean isHoundWin(String[] players, int dimension){
        validateDimension(dimension);
        if (players.length < 2){
            throw new IllegalArgumentException("Not enough players to check for hound victory!");
        }
        int[] fox_position = FoxHoundUtils.strToPos(players[players.length - 1]); // Where the fox is.
        if(fox_position == null){
            throw new IllegalArgumentException("Invalid fox position.");
        }
        int[] up_left = {fox_position[0] - 1, fox_position[1] - 1}; // Tile up and to the left from the fox.
        int[] up_right = {fox_position[0] + 1, fox_position[1] - 1}; // Tile up and to the right from the fox.
        int[] down_right = {fox_position[0] + 1, fox_position[1] + 1}; // Tile down and to the right from the fox.
        int[] down_left = {fox_position[0] + 1, fox_position[1] + 1}; // Tile down and to the left from the fox.
        int[][] possible_fox_movements = {up_left, up_right, down_right, down_left};
        for(int[] possible_move: possible_fox_movements){ // The fox needs to be able to move into one of these.
            if(!tileFilled(players, possible_move) && tileIsOnBoard(dimension, possible_move)){
                return false; // If either tile is empty, hounds haven't won.
            }
        }
        return true; // If not returned false yet, both tiles must be blocked.
    }

    /**
     *  Checks whether any player is on a tile.
     * @param players All players.
     * @param destination Tile in question.
     * @return Whether tile is blocked.
     */
    private static boolean tileFilled(String[] players, int[] destination){
        for(String player: players){
            int[] other_position = strToPos(player);
            if(other_position == null){
                throw new IllegalArgumentException("Invalid player position.");
            }
            if(other_position[0] == destination[0] && other_position[1] == destination[1]){ // No two things on the same square.
                return true;
            }
        }
        return false; // Nothing blocks, so not blocked.
    }

    /**
     *  Checks distance between squares. (Not diagonally)
     * @param start Start position.
     * @param end End position.
     * @return Number of tiles between squares, non diagnoally.
     */
    static int manhattanDistanceBetweenTiles(int[] start, int[] end){
        int x1 = start[0];
        int y1 = start[1];
        int x2 = end[0];
        int y2 = end[1];
        int diff_x = Math.abs(x1-x2); // Distance in x.
        int diff_y = Math.abs(y1-y2); // Distance in y.
        return diff_x + diff_y;
    }

    /**
     * Checks whether the move is valid.
     * @param dim Board size.
     * @param players All players.
     * @param figure Who's trying to move.
     * @param origin Start location.
     * @param destination End location.
     * @return Whether move is valid.
     */
    static boolean isValidMove(int dim, String[] players, char figure, String origin, String destination){
        validateDimension(dim);
        if (players == null){
            throw new NullPointerException();
        }
        if(!FoxHoundUtils.isValidTurn(figure)){
            throw new IllegalArgumentException("Turn is invalid.");
        }

        int[] dest_pos = strToPos(destination); // Convert coords to int array.
        int[] origin_pos = strToPos(origin);
        if(dest_pos == null || origin_pos == null){
            throw new IllegalArgumentException("Badly formed origin or destination.");
        }
        if(!tileIsOnBoard(dim, dest_pos)){
            return false;
        }
        if(tileFilled(players, dest_pos)){
            return false;
        }
        int index_of_moving_player = -1; // Find the player moving.
        for(int i = 0; i < players.length; i++){
            if(origin.equals(players[i])){
                index_of_moving_player = i;
                break;
            }
        }
        if(index_of_moving_player == -1){
            return false; // Not a piece where you want to move from.
        }
        switch (figure){
            case FoxHoundUtils.FOX_FIELD: {
                if(index_of_moving_player != players.length - 1){
                    return false; // Not actually moving the fox.
                }
                break;
            }
            case FoxHoundUtils.HOUND_FIELD: {
                if(index_of_moving_player == players.length - 1){
                    return false; // Not actually moving the hound.
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Figure wasn't hound or fox.");
            }
        }
        // Since we've already checked that the target square is black, we can just check distance to dest.
        if(manhattanDistanceBetweenTiles(origin_pos, dest_pos) != 2){
            return false;
        }
        if(figure == FoxHoundUtils.HOUND_FIELD && (origin_pos[1] - 1) == dest_pos[1]){ // If it's a hound that's trying to move backwards.
            return false;
        }
        return true;
    }
}













