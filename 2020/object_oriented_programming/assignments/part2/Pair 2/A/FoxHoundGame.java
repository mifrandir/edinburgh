import java.util.Scanner;

/**
 * The Main class of the fox hound program.
 *
 * It contains the main game loop where main menu interactions
 * are processed and handler functions are called.
 */
public class FoxHoundGame {
    /**
     * This scanner can be used by the program to read from
     * the standard input.
     *
     * Every scanner should be closed after its use, however, if you do
     * that for StdIn, it will close the underlying input stream as well
     * which makes it difficult to read from StdIn again later during the
     * program.
     *
     * Therefore, it is advisable to create only one Scanner for StdIn
     * over the course of a program and only close it when the program
     * exits. Additionally, it reduces complexity.
     */
    private static final Scanner STDIN_SCAN = new Scanner(System.in);

    /**
     * Swap between fox and hounds to determine the next
     * figure to move.
     *
     * @param currentTurn last figure to be moved
     * @return next figure to be moved
     */
    private static char swapPlayers(char currentTurn) {
        if (currentTurn == FoxHoundUtils.FOX_FIELD) {
            return FoxHoundUtils.HOUND_FIELD;
        } else {
            return FoxHoundUtils.FOX_FIELD;
        }
    }

    /**
     * The main loop of the game. Interactions with the main
     * menu are interpreted and executed here.
     *
     * @param dim the dimension of the game board
     * @param players current position of all figures on the board in board coordinates
     */
    private static void gameLoop(int dim, String[] players) {
        // start each game with the Fox
        char turn = FoxHoundUtils.FOX_FIELD;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n#################################");
            FoxHoundUI.displayBoard(players, dim);

            int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);

            // handle menu choice
            switch (choice) {
                case FoxHoundUI.MENU_MOVE:
                    // Display positions for fidelity
                    FoxHoundUI.listPositions(players);
                    players = doNextMove(dim, turn, players);
                    // Check a player hasn't won here
                    if (FoxHoundUtils.isFoxWin(players[players.length - 1])) {
                        System.out.println("The Fox wins!");
                        return;
                    }
                    if (FoxHoundUtils.isHoundWin(players, dim)) {
                        System.out.println("The Hounds win!");
                        return;
                    }
                    turn = swapPlayers(turn);
                    break;
                case FoxHoundUI.MENU_SAVE:
                    if (!FoxHoundIO.saveGame(players, turn,
                                FoxHoundUI.fileQuery(STDIN_SCAN))) { // if save unsuccessful
                        System.out.println("ERROR: Saving file failed.");
                    }
                    break;
                case FoxHoundUI.MENU_LOAD:
                    char newTurn = FoxHoundIO.loadGame(players, FoxHoundUI.fileQuery(STDIN_SCAN));
                    if (newTurn == '#') {
                        System.out.println("ERROR: Loading from file failed.");
                    } else {
                        turn = newTurn;
                    }
                    break;
                case FoxHoundUI.MENU_EXIT:
                    exit = true;
                    break;
                default:
                    System.err.println("ERROR: invalid menu choice: " + choice);
            }
        }
    }

    /**
     * Performs the move validity loop, then updates the players[] after a valid move
     * @param dim The board dimensions
     * @param turn The player whose turn it is, e.g. 'F' or 'H'
     * @param players The array of all players as board Strings.
     * @return
     */
    private static String[] doNextMove(int dim, char turn, String[] players) {
        String[] proposedMoves = null;
        Boolean validMove = false;
        while (!validMove) {
            proposedMoves = FoxHoundUI.positionQuery(dim, STDIN_SCAN);
            if (FoxHoundUtils.isValidMove(dim, players, turn, proposedMoves[0], proposedMoves[1])) {
                validMove = true;
            } else {
                System.out.println("ERROR: Invalid move. Try again!");
            }
        }

        // move is valid, now we replace
        for (int i = 0; i < players.length; i++) // Assuming this will hit, as positionQuery
                                                 // requires the correct figure at the given origin
        {
            if (players[i].equals(proposedMoves[0])) {
                players[i] = proposedMoves[1];
            }
        }

        return players;
    }

    /**
     * Entry method for the Fox and Hound game.
     *
     * The dimensions of the game board can be passed in as
     * optional command line argument.
     *
     * If no argument is passed, a default dimension of
     * {@value FoxHoundUtils#DEFAULT_DIM} is used.
     *
     * Dimensions must be between {@value FoxHoundUtils#MIN_DIM} and
     * {@value FoxHoundUtils#MAX_DIM}.
     *
     * @param args contain the command line arguments where the first can be
     * board dimensions.
     */
    public static void main(String[] args) {
        // Specification calls for dimension value to be assignable through args array
        int dimension;
        try {
            dimension = Integer.parseInt(args[0]);
        } catch (IndexOutOfBoundsException i) {
            dimension = FoxHoundUtils.DEFAULT_DIM;
        }

        String[] players = FoxHoundUtils.initialisePositions(dimension);
        gameLoop(dimension, players);

        // Close the scanner reading the standard input stream
        STDIN_SCAN.close();
    }
}