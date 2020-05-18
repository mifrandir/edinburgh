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
     * Generates starting positions for The Hounds and The Fox given a board dimension
     * @param dimension The board dimension
     * @return The array of all players as board Strings.
     * @throws IllegalArgumentException If given an out-of-bounds dimension
     */
    public static String[] initialisePositions(int dimension) throws IllegalArgumentException
    {
        if(dimension < MIN_DIM || dimension > MAX_DIM)
        {throw new IllegalArgumentException("Invalid Dimension supplied.");}

        String[] finalArray = new String[((int) dimension / 2) + 1];

        for(int i = 0; i < (int) dimension / 2; i++) //for each hound
        {
            char letter = (char) (66 + 2 * i); //B, D, F, ...
            finalArray[i] =  letter  + "1";    //B1, D1, F1, ...
            //System.out.println(finalArray[i]); //Debug
        }

        //now fox...
        finalArray[((int) dimension / 2)] = ((char) (65 + Math.ceil(dimension / 2)) ) + Integer.toString(dimension);
        //System.out.println( finalArray[((int) dimension / 2) ]);
        return finalArray;
    }

    /**
     * Returns true if the fox has reached the top row
     * @param foxPos The Fox's board position
     * @return whether The Fox has won
     */
    public static boolean isFoxWin(String foxPos) {
        return foxPos.substring(1).equals("1"); //fox wins if position ends in 1
        //substring of null gives nullPointer :-)
    }

    /**
     * Returns true if The Fox cannot move, i.e. The Hounds have won
     * @param players The array of all players as board Strings.
     * @param dimension The board dimension
     * @return whether The Hounds have won
     */
    public static boolean isHoundWin(String[] players, int dimension) {
        //Hounds win if the fox has no valid moves in the four diagonals around it.
        //Generating valid moves:
        String foxpos = players[players.length - 1];
        char letter = foxpos.toCharArray()[0];
        int number = Integer.parseInt(foxpos.substring(1));
        Boolean validMovePossible = false;
        for(int i=0;i<4;i++)
        {
            //Just to give all combinations of +-1 for a and b
            int a = 1;
            int b = 1;
            if(i % 2 == 0)
            {
                a = -1;
            }
            if((int)(i / 2) == 1)
            {
                b = -1;
            }

            String tentativeMove = (char) ((int) letter + a) + "" + (number + b);

            if(isValidMove(dimension, players, FOX_FIELD, foxpos, tentativeMove))
            {
                validMovePossible = true;
            }
        }

        return !validMovePossible; //i.e. Fox can't move

    }

    /**
     * Returns true if a given move is valid
     * @param dim the board dimensions
     * @param players The array of all players as board Strings.
     * @param figure The character being moved - 'F' or 'H'
     * @param origin The board position of the movee
     * @param dest The proposed new board position
     * @return whether this move is valid
     */
    public static boolean isValidMove(int dim, String[] players, char figure, String origin, String dest)
    {
        //Error conditions:
        if(dim < MIN_DIM || dim > MAX_DIM)
        {throw new IllegalArgumentException("Invalid Dimension supplied.");}
        if(figure != FOX_FIELD && figure != HOUND_FIELD)
        {throw new IllegalArgumentException("Must supply a valid figure char, i.e. F or H");}
        if(players == null)
        {throw new NullPointerException("uninitialised players[]");}

        //Check destination & origin is within bounds:
        if(!withinBounds(origin, dim) || !withinBounds(dest, dim))
        {
            return false;
        }

        //Check given origin is occupied
        Boolean occupiedOrigin = false;
        for(int i = 0; i < players.length; i++)
        {
            if(origin.equals(players[i]))
            {
                occupiedOrigin = true;
            }
        }
        if(!occupiedOrigin)
        {
            return false;
        }

        //Check given square is not occupied
        for(int i = 0; i < players.length; i++)
        {
            if(dest.equals(players[i]))
            {
                return false;
            }
        }
        //Check the correct figure is selected
        if(players[players.length - 1].equals(origin)) //if fox moving
        {
            if(figure == HOUND_FIELD) {
                return false;
            }
        } else { //hound moving
            if(figure == FOX_FIELD) {
                return false;
            }
        }

        //The difference in location relative to the origin position
        int dx = 0;
        int dy = 0;

        char o1 = origin.toCharArray()[0];
        char d1 = dest.toCharArray()[0];

        int o2 = Integer.parseInt(origin.substring(1));
        int d2 = Integer.parseInt(dest.substring(1));

        dx = ((int) d1) - ((int) o1);
        dy = d2 - o2;

        if(dy == 1) //if moving down
        {
            if(Math.abs(dx) == 1)
            {
                return true;
            } else {return false;}
        } else {
            //Could either be a fox moving up or an invalid move
            if(figure == FOX_FIELD)
            {
                if(dy == -1)
                {
                    if(Math.abs(dx) == 1)
                    {
                        return true;
                    } else {return false;}
                }
            } else {return false;}
        }

        System.out.println("Should never be called!");
        return false;
    }

    /**
     * A helper function which determines whether a given position is on the game board
     * @param position The position to check
     * @param dim the board dimensions
     * @return whether the position is on the board
     */
    public static boolean withinBounds(String position, int dim) // public as we use this in positionQuery
    {
        int column = (int) position.toCharArray()[0];
        int row = Integer.parseInt(position.substring(1));

        if(column - ((int) 'A') < 0 || column - ((int) 'A') > dim)
        {
            return false;
        }
        if(row < 0 || row > dim)
        {
            return false;
        }

        return true;
    }

}
